package com.snapbizz.snapturbo.commons.permissions.domain.repository

interface PermissionManager {
    fun requiredPermissions() : List<String>
    fun hasAllPermissions() : Boolean
}