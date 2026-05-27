package com.snapbizz.snapturbo.onboarding.login.data.remote.model

import com.google.gson.annotations.SerializedName

data class GenerateTokenRequest(
    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("device_id")
    val deviceId: String?,

    @SerializedName("store_id")
    val storeId: Long
)