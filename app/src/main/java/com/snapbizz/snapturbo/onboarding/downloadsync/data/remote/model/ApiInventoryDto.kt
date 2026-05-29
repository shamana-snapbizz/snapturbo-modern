package com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiInventoryDto(

    @SerializedName("minimum_base_quantity")
    val minimumBaseQuantity: Int?,

    @SerializedName("reorder_point")
    val reorderPoint: Int?,

    val quantity: Int,

    @SerializedName("product_code")
    val productCode: String,

    @SerializedName("is_deleted")
    val isDeleted: Boolean,

    @SerializedName("batch_id")
    val batchId: Long
)