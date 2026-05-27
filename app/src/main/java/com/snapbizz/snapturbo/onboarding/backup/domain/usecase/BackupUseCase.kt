package com.snapbizz.snapturbo.onboarding.backup.domain.usecase

import com.snapbizz.snapturbo.onboarding.backup.data.repository.BackupRepositoryImpl
import javax.inject.Inject

class BackupUseCase @Inject constructor(
    private val repository: BackupRepositoryImpl
){
    suspend fun runAutoBackup() = repository.runAutoBackup()
    suspend fun runManualBackup() = repository.runManualBackup()
}