package com.snapbizz.snapturbo.onboarding.login.domain.usecase

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.VerifyOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.repository.LoginRepository
import javax.inject.Inject

class VerifyOtpUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(otp: Long): NetworkResult<VerifyOtpResult> {
        return loginRepository.verifyOtp(otp)
    }
}