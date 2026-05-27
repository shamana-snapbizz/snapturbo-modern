package com.snapbizz.snapturbo.commons

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.language.LocaleManager
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceInfoProvider
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceSession
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class SnapApp : Application() {

    @Inject
    lateinit var deviceInfoProvider: DeviceInfoProvider

    @Inject
    lateinit var deviceSession: DeviceSession

    @Inject
    lateinit var appDataStore: AppDataStore
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            deviceSession.deviceId = appDataStore.deviceIdFlow.firstOrNull()
            deviceSession.storeId = appDataStore.storeId.firstOrNull()
            deviceSession.accessToken = appDataStore.accessToken.firstOrNull()
            deviceSession.authToken = appDataStore.authToken.firstOrNull()
        }
    }


    override fun attachBaseContext(base: Context) {
        // 🔥 Process-level locale (cold start / restart safe)
        super.attachBaseContext(LocaleManager.wrapContext(base))
    }
}