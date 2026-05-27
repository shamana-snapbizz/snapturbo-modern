package com.snapbizz.snapturbo.onboarding.registration.data.repository

import android.util.Log
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.registration.data.remote.RegistrationApiService
import com.snapbizz.snapturbo.onboarding.registration.data.remote.model.RegistrationRequest
import com.snapbizz.snapturbo.onboarding.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val api: RegistrationApiService
) : RegistrationRepository {

    override suspend fun registerStore(
        deviceId: String,
        accessToken: String,
        request: RegistrationRequest
    ): NetworkResult<Unit> {

        return try {

            val response =
                api.editStoreDetails(

                    deviceId = deviceId,

                    accessToken = accessToken,

                    storeId = request.storeId,

                    request = request,

                    queryStoreId = request.storeId,

                    )

            Log.d(
                "RegistrationAPI",
                "code=${response.code()} body=${response.body()}"
            )

            val body = response.body()

            if (
                response.isSuccessful &&
                body?.status.equals("success", true)
            ) {
                NetworkResult.Success(Unit)
            } else {
                NetworkResult.Error(
                    body?.status ?: "Registration failed"
                )
            }
        } catch (e: Exception) {

            NetworkResult.Error(
                e.message ?: "Unknown error"
            )
        }
    }
}