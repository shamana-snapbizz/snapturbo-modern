package com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class InventoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val productCode: String,

    val batchId: Long,

    val quantity: Int,

    val reorderPoint: Int?,

    val minimumBaseQuantity: Int?,

    val isDeleted: Boolean
)