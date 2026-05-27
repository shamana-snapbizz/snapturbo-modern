package com.snapbizz.snapturbo.onboarding.login.data.remote

import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateOtpRequest
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateOtpResponse
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.VerifyOtpApiModel
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.VerifyOtpApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("otp_generation")
    suspend fun generateOtp(
        @Body request: GenerateOtpRequest
    ): Response<GenerateOtpResponse>

    @POST("device_registration")
    suspend fun verifyOtp(
        @Body request: VerifyOtpApiModel
    ): Response<VerifyOtpApiResponse>

}
