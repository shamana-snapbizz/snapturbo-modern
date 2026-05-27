package com.snapbizz.snapturbo.onboarding.login.domain.model

import com.google.gson.annotations.SerializedName

data class GenerateOtpRequest(

    @SerializedName("build_nos")
    val buildNos: String,

    @SerializedName("model_no")
    val modelNo: String,

    @SerializedName("os_type")
    val osType: String,

    @SerializedName("os_version")
    val osVersion: String,

    @SerializedName("device_id")
    val deviceId: String,

    @SerializedName("device_type")
    val deviceType: String,

    @SerializedName("store_phone")
    val storePhone: Long
)

