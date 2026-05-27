package com.snapbizz.snapturbo.onboarding.login.data.remote

import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateTokenRequest
import com.snapbizz.snapturbo.onboarding.login.data.remote.model.GenerateTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenApiService {
    @POST("generate_token")
    suspend fun generateToken(
        @Body request: GenerateTokenRequest
    ): Response<GenerateTokenResponse>
}