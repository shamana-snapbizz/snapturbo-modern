package com.snapbizz.snapturbo.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snapbizz.snapturbo.commons.utils.extensions_functions.toastShort
import com.snapbizz.snapturbo.splash.presentation.ui.SplashScreen
import com.snapbizz.snapturbo.splash.presentation.viewmodel.SplashViewModel

fun NavGraphBuilder.splashRoute(
    navController: NavController
) {
    composable(ScreenRoute.Splash.route) {
        val context = LocalContext.current
        val viewModel: SplashViewModel = hiltViewModel()
        val state by viewModel.state.collectAsStateWithLifecycle()

        // ✅ One-time navigation collector
        LaunchedEffect(Unit) {
            viewModel.navigation.collect {
                navController.navigate(ScreenRoute.Auth.Login.route) {
                    popUpTo(ScreenRoute.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        SplashScreen(
            state = state,
            permissions = viewModel.requiredPermissions(),
            onStart = viewModel::onStart,
            onPermissionsGranted = viewModel::onStart,
            onPermissionsDeniedPermanently = {
                context.toastShort("Some features may not work")
                viewModel.continueWithoutBlockingPermissions()
            }
        )
    }
}

