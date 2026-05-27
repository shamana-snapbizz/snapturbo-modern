package com.snapbizz.snapturbo.onboarding.login.data.remote.model

import com.google.gson.annotations.SerializedName

data class VerifyOtpApiModel(
    val otp: Long,
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("store_phone")
    val storePhone: Long
)
