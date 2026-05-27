package com.snapbizz.snapturbo.onboarding.login.data.device


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceSession @Inject constructor() {

    @Volatile var deviceId: String? = null
    @Volatile var storeId: Long? = null
    @Volatile var storeNumber: Long? = null
    @Volatile var accessToken: String? = null
    @Volatile var authToken: String? = null

    fun isReady(): Boolean = deviceId != null
}

