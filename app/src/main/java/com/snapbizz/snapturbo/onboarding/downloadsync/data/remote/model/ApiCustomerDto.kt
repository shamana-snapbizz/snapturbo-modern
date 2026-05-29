package com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiCustomerDto(

    val address: String?,

    val name: String?,

    val phone: Long,

    val email: String?,

    @SerializedName("credit_limit")
    val creditLimit: Int,

    @SerializedName("is_disabled")
    val isDisabled: Boolean,

    val gstin: String?,

    @SerializedName("membership_id")
    val membershipId: String?
)