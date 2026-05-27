package com.snapbizz.snapturbo.onboarding.login.domain.model

import com.google.gson.annotations.SerializedName

data class VerifyOtp(
    val otp: Int,
    @SerializedName("device_id")
    val deviceId: String,
    @SerializedName("store_phone")
    val storePhone: Long
)
