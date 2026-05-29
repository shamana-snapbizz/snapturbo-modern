package com.snapbizz.snapturbo.onboarding.downloadsync.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiProductDto(

    @SerializedName("product_code")
    val productCode: Long,

    val name: String?,

    val mrp: Int,

    val uom: String?,

    val image: String?,

    @SerializedName("vat_rate")
    val vatRate: Float,

    @SerializedName("category_id")
    val categoryId: Int?,

    @SerializedName("is_deleted")
    val isDeleted: Boolean,

    val barcode: Long,

    @SerializedName("brand_name")
    val brandName: String?,

    @SerializedName("company_name")
    val companyName: String?
)