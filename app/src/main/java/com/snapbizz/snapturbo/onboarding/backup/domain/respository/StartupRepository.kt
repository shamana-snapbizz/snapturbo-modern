package com.snapbizz.snapturbo.onboarding.backup.domain.respository

interface StartupRepository {
    suspend fun prepareStorage()
    suspend fun ensureDeviceId()
}