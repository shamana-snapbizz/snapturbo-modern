package com.snapbizz.snapturbo.onboarding.login.data.device

import android.os.Build
import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceInfoProviderImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : DeviceInfoProvider {

    override val buildNo: String
        get() = "${BuildConfig.APP_VERSION_PREFIX}${BuildConfig.VERSION_NAME}"

    override val osType: String = "Android"

    override val modelNo: String
        get() = Build.MODEL ?: "unknown"

    override val osVersion: String
        get() = Build.VERSION.SDK_INT.toString()

    override suspend fun deviceId(): String =
        appDataStore.deviceIdFlow.firstOrNull()
            ?: throw IllegalStateException("DeviceId not found")

    override suspend fun storeNumber(): Long =
        appDataStore.storeNumber.first()

    override suspend fun storeId(): Long =
        appDataStore.storeId.first()

    override suspend fun accessToken(): String =
        appDataStore.accessToken.first()
            ?: throw IllegalStateException("AccessToken missing")

    override suspend fun authToken(): String =
        appDataStore.authToken.first()
            ?: throw IllegalStateException("AuthToken missing")
}
