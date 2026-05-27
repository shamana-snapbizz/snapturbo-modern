package com.snapbizz.snapturbo.onboarding.registration.domain.repository

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.registration.data.remote.model.RegistrationRequest


interface RegistrationRepository {
    suspend fun registerStore(
        deviceId: String,
        accessToken: String,
        request: RegistrationRequest
    ): NetworkResult<Unit>
}