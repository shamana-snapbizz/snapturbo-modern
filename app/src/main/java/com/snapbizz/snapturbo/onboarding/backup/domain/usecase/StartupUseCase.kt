package com.snapbizz.snapturbo.onboarding.backup.domain.usecase

import com.snapbizz.snapturbo.onboarding.backup.domain.respository.StartupRepository
import javax.inject.Inject

class StartupUseCase @Inject constructor(
    private val repository: StartupRepository
) {

    suspend fun initializeStorageAndDevice() {
        repository.ensureDeviceId()
        repository.prepareStorage()
    }
}