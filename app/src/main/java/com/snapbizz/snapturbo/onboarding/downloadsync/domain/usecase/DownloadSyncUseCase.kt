package com.snapbizz.snapturbo.onboarding.downloadsync.domain.usecase

import com.snapbizz.snapturbo.onboarding.downloadsync.data.repository.DownloadSyncRepository
import javax.inject.Inject

class DownloadSyncUseCase @Inject constructor(
    private val repository: DownloadSyncRepository
) {

    suspend operator fun invoke(): Result<Unit> {
        return repository.performDownloadSync()
    }
}