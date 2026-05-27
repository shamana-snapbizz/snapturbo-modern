package com.snapbizz.snapturbo.onboarding.backup.domain.respository

interface BackupRepository {
    suspend fun runAutoBackup(): Result<Unit>
    suspend fun runManualBackup(): Result<Unit>
}