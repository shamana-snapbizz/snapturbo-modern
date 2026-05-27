package com.snapbizz.snapturbo.onboarding.registration.data.remote.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(

    @SerializedName("store_id")
    val storeId: Long,

    @SerializedName("is_disabled")
    val isDisabled: Boolean,

    val phone: Long,

    @SerializedName("salesperson_number")
    val salesPersonNumber: Long?,

    val state: String,

    val city: String,

    val pincode: Int,

    val name: String,

    val address: String,

    @SerializedName("retailer_id")
    val retailerId: Long,

    @SerializedName("state_id")
    val stateId: Int,

    val tin: Long?,

    @SerializedName("retailer_gstin")
    val retailerGstin: String?
)