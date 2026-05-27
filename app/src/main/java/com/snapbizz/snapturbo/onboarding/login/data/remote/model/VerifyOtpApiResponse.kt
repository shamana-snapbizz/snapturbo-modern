package com.snapbizz.snapturbo.onboarding.login.data.remote.model

import com.google.gson.annotations.SerializedName

data class VerifyOtpApiResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("store_details")
    val storeDetails: StoreDetails?,
    @SerializedName("retailer_details")
    val retailerDetails: RetailerDetails?,
    @SerializedName("store_type")
    val storeType: List<String>?,
    @SerializedName("pos_id")
    val posId: Int,
    val status: String,
    var token : String
)

data class StoreDetails(
    @SerializedName("store_id")
    val storeId: Long,
    val name: String,
    val phone: Long,

    @SerializedName("salesperson_number")
    val salespersonNumber: Long,

    @SerializedName("is_disabled")
    val isDisabled: Boolean,

    val tin: Long,
    val address: String,
    val city: String,
    val state: String,
    val pincode: Long,

    @SerializedName("device_type")
    val deviceType: String,

    @SerializedName("neo_subscription_type")
    val neoSubscriptionType: String,

    @SerializedName("is_item_master")
    val isItemMaster: Boolean,

    @SerializedName("state_id")
    val stateId: Int
)

data class RetailerDetails(
    @SerializedName("retailer_id")
    val retailerId: Long,
    val name: String,
    val address: String,
    val email: String,
    val gstin: String
)

