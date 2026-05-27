package com.snapbizz.snapturbo.onboarding.registration.domain.usecase

import com.snapbizz.snapturbo.onboarding.registration.data.remote.model.RegistrationRequest
import com.snapbizz.snapturbo.onboarding.registration.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {

    suspend operator fun invoke(
        deviceId: String,
        accessToken: String,
        request: RegistrationRequest
    ) =
        repository.registerStore(
            deviceId,
            accessToken,
            request
        )
}