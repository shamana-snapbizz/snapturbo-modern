package com.snapbizz.snapturbo.onboarding.login.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey
    val username: String,

    val password: String,

    val name: String,

    val roleId: Int,

    val isDisabled: Boolean,

    val createdAt: Long,

    val updatedAt: Long
)
