package com.snapbizz.snapturbo.onboarding.login.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the request body for generating an OTP.
 *
 * This class follows Kotlin's camelCase convention for property names.
 * The @SerializedName annotation maps these properties to the snake_case keys
 * expected by the API endpoint during JSON serialization.
 */
data class GenerateOtpRequest(
    @SerializedName("build_no")
    val buildNo: String,

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
