package com.snapbizz.snapturbo.splash.presentation.ui

data class SplashUiState(
    val isloading : Boolean = true,
    val allPermissionsGranted: Boolean = false,
    val showPermissionDialog: Boolean = false,
    val error: String? = null,
    val navigateNext: Boolean = false
)