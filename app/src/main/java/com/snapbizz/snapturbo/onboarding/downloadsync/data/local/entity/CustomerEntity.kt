package com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(

    @PrimaryKey
    val phone: Long,

    val name: String?,

    val address: String?,

    val email: String?,

    val creditLimit: Int,

    val gstin: String?,

    val membershipId: String?,

    val isDisabled: Boolean
)