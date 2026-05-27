package com.snapbizz.snapturbo.commons.permissions.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.snapbizz.snapturbo.commons.permissions.domain.repository.PermissionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.plusAssign

@Singleton
class PermissionManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PermissionManager {

    override fun requiredPermissions(): List<String> {
        val permissions = mutableListOf<String>()

        // ❌ Restricted on Android 13+
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S) {
            permissions += Manifest.permission.READ_PHONE_STATE
        }

        // Optional (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions += Manifest.permission.POST_NOTIFICATIONS
        }

        return permissions
    }

    override fun hasAllPermissions(): Boolean {
        return requiredPermissions().all { permission ->
            val granted =
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED

            // 🔥 Never block on restricted permission
            if (
                permission == Manifest.permission.READ_PHONE_STATE &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            ) {
                true
            } else {
                granted
            }
        }
    }
}