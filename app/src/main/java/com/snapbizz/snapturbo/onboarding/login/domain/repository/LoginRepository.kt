package com.snapbizz.snapturbo.onboarding.login.domain.repository

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.GenerationOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.VerifyOtpResult

interface LoginRepository {
    suspend fun generateOtp(storePhone: Long): NetworkResult<GenerationOtpResult>
    suspend fun verifyOtp(otp: Long): NetworkResult<VerifyOtpResult>
}
