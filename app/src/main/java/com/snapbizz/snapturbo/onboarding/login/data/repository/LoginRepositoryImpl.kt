package com.snapbizz.snapturbo.onboarding.login.data.repository

import android.os.Build
import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.commons.utils.SnapConstants
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceInfoProvider
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceSession
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateOtpRequest
import com.snapbizz.snapturbo.onboarding.login.data.remote.LoginApiService
import com.snapbizz.snapturbo.onboarding.login.data.remote.TokenApiService
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.VerifyOtpApiModel
import com.snapbizz.snapturbo.onboarding.login.data.remote.error.HttpErrorMapper
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateTokenRequest
import com.snapbizz.snapturbo.onboarding.login.domain.model.GenerationOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.VerifyOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.toDomain
import com.snapbizz.snapturbo.onboarding.login.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApiService,          // V2
    private val tokenApi: TokenApiService,      // V3
    private val deviceInfoProvider: DeviceInfoProvider

) : LoginRepository {

    override suspend fun generateOtp(
        storePhone: Long
    ): NetworkResult<GenerationOtpResult> {

        return try {
            val request = GenerateOtpRequest(
                buildNo = deviceInfoProvider.buildNo,
                modelNo = deviceInfoProvider.modelNo,
                osType = deviceInfoProvider.osType,
                osVersion = deviceInfoProvider.osVersion,
                deviceId = deviceInfoProvider.deviceId(),
                deviceType = SnapConstants.DEVICE_TYPE,
                storePhone = storePhone
            )
            val response = loginApi.generateOtp(request)

            if (response.isSuccessful) {
                val body = response.body()
                    ?: return NetworkResult.Error("Empty response from server")

                NetworkResult.Success(
                    GenerationOtpResult(
                        status = body.status,
                        message = body.message
                    )
                )
            } else {
                NetworkResult.Error(HttpErrorMapper.map(response.code()))
            }

        } catch (e: Exception) {
            NetworkResult.Error("Network error")
        }
    }

    override suspend fun verifyOtp(
        otp: Long
    ): NetworkResult<VerifyOtpResult> {


        val deviceId = deviceInfoProvider.deviceId()
            ?: return NetworkResult.Error("Device not initialized")

        val storeNumber = deviceInfoProvider.storeNumber()
            ?: return NetworkResult.Error("Store not initialized")

        return try {
            // 1️⃣ Verify OTP
            val verifyRequest = VerifyOtpApiModel(
                otp = otp,
                deviceId = deviceId,
                storePhone = storeNumber
            )

            val verifyResponse = loginApi.verifyOtp(verifyRequest)

            val verifyBody = verifyResponse.body()
                ?: return NetworkResult.Error("Empty OTP response")

            if (!verifyBody.status.equals("Success", ignoreCase = true)) {
                return NetworkResult.Error(verifyBody.status)
            }

            // 2️⃣ Generate Token
            val tokenRequest = verifyBody.storeDetails?.let {
                GenerateTokenRequest(
                    accessToken = verifyBody.accessToken,
                    deviceId = deviceId,
                    storeId = it.storeId
                )
            }

            val tokenResponse = tokenRequest?.let { tokenApi.generateToken(it) }

            val tokenBody = tokenResponse?.body()
                ?: return NetworkResult.Error("Empty token response")

            verifyBody.token = tokenBody.token

            NetworkResult.Success(verifyBody.toDomain())

        } catch (e: Exception) {
            NetworkResult.Error("Verification failed")
        }
    }

}

