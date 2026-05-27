package com.snapbizz.snapturbo.ui.root

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.internet.NetworkConnectivityImpl
import com.snapbizz.snapturbo.commons.internet.NetworkStatus
import com.snapbizz.snapturbo.commons.language.LocalAppResources
import com.snapbizz.snapturbo.commons.utils.resources.AppResourcesImpl
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LanguageViewModel
import com.snapbizz.snapturbo.ui.components.snap_dialog.InternetDialog
import com.snapbizz.snapturbo.ui.navigation.AppNavGraph
import com.snapbizz.snapturbo.ui.snackbar.SnackbarEvent
import com.snapbizz.snapturbo.ui.snackbar.SnackbarManager
import com.snapbizz.snapturbo.ui.snackbar.SnackbarType
import com.snapbizz.snapturbo.R // <-- ADD THIS LINE
import com.snapbizz.snapturbo.onboarding.registration.presentation.viewmodel.RegistrationViewModel
import com.snapbizz.snapturbo.ui.snackbar.AppSnackbarHost

@Composable
fun AppRoot(
    appResources: AppResourcesImpl,
    appDataStore: AppDataStore,
    networkConnectivity: NetworkConnectivityImpl
) {

    // 🔥 Language
    val languageViewModel: LanguageViewModel = hiltViewModel()
    val lang by languageViewModel.language.collectAsStateWithLifecycle()

    val localizedResources = remember(lang) {
        appResources.withLanguage(lang)
    }

    // 🔥 Device ID
    val deviceId by appDataStore.deviceIdFlow
        .collectAsStateWithLifecycle(initialValue = null)

    // 🔥 Network status
    val networkStatus by networkConnectivity.status
        .collectAsStateWithLifecycle()

    // 🔥 Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }
    var currentSnackbarEvent by remember { mutableStateOf<SnackbarEvent?>(null) }

    // 🔥 Collect snackbar events
    LaunchedEffect(Unit) {
        SnackbarManager.messages.collect { event ->
            currentSnackbarEvent = event

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.actionLabel,
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.onAction?.invoke()
            }

            currentSnackbarEvent = null
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            ) { snackbarData ->

                val isError = currentSnackbarEvent?.type == SnackbarType.ERROR

                Snackbar(
                    containerColor = if (!isError)
                        Color(0xFFD32F2F) // 🔴 Error red
                    else
                        Color(0xFF323232),
                    contentColor = Color.White
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!isError) {
                            Icon(
                                imageVector = ImageVector.vectorResource(
                                    id = R.drawable.alert // ✅ VECTOR DRAWABLE
                                ),
                                contentDescription = "Error",
                                tint = Color.White,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }

                        Text(
                            text = snackbarData.visuals.message,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            CompositionLocalProvider(
                LocalAppResources provides localizedResources
            ) {
//                LockLandScape()
//                AppSnackbarHost()
                AppNavGraph(languageViewModel)
            }

            when (networkStatus) {
                is NetworkStatus.Unavailable ->
                    InternetDialog(deviceId, appResources)
                else -> Unit
            }
        }
    }
}


@Composable
fun LockLandScape() {
    val context = LocalContext.current
    val activity = context as Activity

    DisposableEffect(Unit) {
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}
