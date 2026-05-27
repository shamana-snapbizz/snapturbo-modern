package com.snapbizz.snapturbo.ui.snackbar

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.ui.theme.SnapColor
import timber.log.Timber

@Composable
fun AppSnackbarHost() {
    val topHostState = remember { SnackbarHostState() }
    val centerHostState = remember { SnackbarHostState() }
    val bottomHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        SnackbarManager.messages.collect { event ->
            Timber.tag("SNACKBAR")
                .d("Position=${event.position}")
            SnackbarManager.messages.collect { event ->
                when (event.position) {
                    SnackbarPosition.TOP -> {
                        bottomHostState.currentSnackbarData?.dismiss()
                        centerHostState.currentSnackbarData?.dismiss()
                        topHostState.showSnackbar(event.message)
                    }

                    SnackbarPosition.CENTER -> {
                        topHostState.currentSnackbarData?.dismiss()
                        bottomHostState.currentSnackbarData?.dismiss()
                        centerHostState.showSnackbar(event.message)
                    }

                    SnackbarPosition.BOTTOM -> {
                        topHostState.currentSnackbarData?.dismiss()
                        centerHostState.currentSnackbarData?.dismiss()
                        bottomHostState.showSnackbar(event.message)
                    }
                }

            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // 🔝 TOP Snackbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
        ) {
            SnackbarHost(
                hostState = topHostState
            ) { data ->
                CustomSnackbar(data, SnackbarPosition.TOP)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(bottom = 16.dp)
        ) {
            SnackbarHost(
                hostState = centerHostState
            ) { data ->
                CustomSnackbar(data, SnackbarPosition.CENTER)
            }
        }
        // 🔽 BOTTOM Snackbar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            SnackbarHost(
                hostState = bottomHostState
            ) { data ->
                CustomSnackbar(data, SnackbarPosition.BOTTOM)
            }
        }
    }
}

@Composable
fun CustomSnackbar(
    data: SnackbarData,
    position: SnackbarPosition
) {
    Snackbar(
        containerColor = when (position) {
            SnackbarPosition.TOP -> SnapColor.primaryColor
            SnackbarPosition.BOTTOM -> Color(0xFF1E88E5)
            SnackbarPosition.CENTER ->  SnapColor.ToastErrorBorder
        },
        contentColor = Color.White
    ) {
        Text(text = data.visuals.message)
    }
}
