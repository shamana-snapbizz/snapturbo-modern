package com.snapbizz.snapturbo.commons.utils.deviceid

import android.annotation.SuppressLint
import android.content.Context
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.absoluteValue
import android.provider.Settings

@Singleton
class DeviceIdProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val appDataStore: AppDataStore
) {

    @SuppressLint("HardwareIds")
    suspend fun getDeviceId(): String {
        //  Cached?
        appDataStore.deviceIdFlow.firstOrNull()?.let { return it }

        //  Read ANDROID_ID (Play-safe)
        val androidId = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )

        //  Generate numeric ID
        val numericId = when {
            androidId.isNullOrBlank() -> fallbackUuid()
            androidId == "9774d56d682e549c" -> fallbackUuid() // known broken value
            else -> DeviceIdGenerator.generateNumericId("1762412363310912")
        }

        // Persist forever
        appDataStore.saveDeviceId("1762412363310912")
        return numericId
    }

    private fun fallbackUuid(): String =
        UUID.randomUUID().toString()
            .hashCode()
            .toLong()
            .absoluteValue
            .toString()
}
