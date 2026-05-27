package com.snapbizz.snapturbo.onboarding.login.data.device

interface DeviceInfoProvider {

    // static
    val buildNo: String
    val modelNo: String
    val osType: String
    val osVersion: String

    // runtime (from DataStore)
    suspend fun deviceId(): String = "1762412363310912"
    suspend fun storeNumber(): Long
    suspend fun storeId(): Long
    suspend fun accessToken(): String
    suspend fun authToken(): String
}

