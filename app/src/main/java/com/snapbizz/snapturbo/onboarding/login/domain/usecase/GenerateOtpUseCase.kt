package com.snapbizz.snapturbo.onboarding.login.domain.usecase

import com.snapbizz.snapturbo.onboarding.login.domain.repository.LoginRepository
import javax.inject.Inject

class GenerateOtpUseCase @Inject constructor(
    private val otpRepository: LoginRepository
) {
    suspend operator fun invoke(storePhone: Long) = otpRepository.generateOtp(storePhone)
}