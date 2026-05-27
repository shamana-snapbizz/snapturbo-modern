package com.snapbizz.snapturbo.commons.permissions.ui

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun PermissionHandler(
    permissions: List<String>,
    onGranted: () -> Unit,
    onDeniedTemporarily: () -> Unit,
    onDeniedPermanently: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as Activity

    val deniedPermissions = remember {
        permissions.filter {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) != PackageManager.PERMISSION_GRANTED
        }
    }

    if (deniedPermissions.isEmpty()) {
        LaunchedEffect(Unit) { onGranted() }
        return
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->

        val denied = result.filterValues { !it }.keys

        if (denied.isEmpty()) {
            onGranted()
        } else {
            val permanentlyDenied = denied.any {
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, it)
            }

            if (permanentlyDenied) {
                onDeniedPermanently()
            } else {
                onDeniedTemporarily()
            }
        }
    }

    LaunchedEffect(deniedPermissions) {
        launcher.launch(deniedPermissions.toTypedArray())
    }
}