package com.snapbizz.snapturbo.splash.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.permissions.ui.PermissionHandler

@Composable
fun SplashScreen(
    state: SplashUiState,
    permissions: List<String>,
    onStart: () -> Unit,
    onPermissionsGranted: () -> Unit,
    onPermissionsDeniedPermanently: () -> Unit
) {

    LaunchedEffect(Unit) {
        onStart()
    }


    if (state.showPermissionDialog) {
        PermissionHandler(
            permissions = permissions,
            onGranted = onPermissionsGranted,
            onDeniedTemporarily = {
                // ✅ DO NOTHING or show explanation UI
                // ❌ NO TOAST
            },
            onDeniedPermanently = {
                onPermissionsDeniedPermanently()
            }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.turbo_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp)
        )
    }
}



