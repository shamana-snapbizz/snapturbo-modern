package com.snapbizz.snapturbo.commons.permissions.domain.usecase

import com.snapbizz.snapturbo.commons.permissions.domain.repository.PermissionManager
import javax.inject.Inject

class CheckInitialPermissionsUseCase @Inject constructor(
    private val permissionManager: PermissionManager
) {
    operator fun invoke() : Boolean = permissionManager.hasAllPermissions()
}