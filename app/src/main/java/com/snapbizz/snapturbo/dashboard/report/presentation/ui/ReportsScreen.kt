package com.snapbizz.snapturbo.dashboard.report.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.ui.navigation.ScreenRoute

@Composable
fun ReportsScreen() {
    @Composable
    fun LoginScreen(
//    mobile: String,
//    onMobileChange: (String) -> Unit,
        onRegisterClick: (ScreenRoute) -> Unit,
        /* deviceId: String,
         appVersion: String*/
    ) {
        var showOtp by rememberSaveable { mutableStateOf(false) }
        var mobile by rememberSaveable { mutableStateOf("") }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            // FULL SCREEN BACKGROUND (this removes white space)
            Image(
                painter = painterResource(R.drawable.turbo_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.8f
            )

        }
    }
}