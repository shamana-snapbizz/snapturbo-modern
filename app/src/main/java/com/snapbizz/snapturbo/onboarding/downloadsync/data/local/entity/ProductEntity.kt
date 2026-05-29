package com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey
    val productCode: String,

    val name: String,

    val mrp: Int,

    val uom: String?,

    val image: String?,

    val vatRate: Float,

    val categoryId: Int?,

    val barcode: Long,

    val brandName: String?,

    val companyName: String?,

    val isDeleted: Boolean
)