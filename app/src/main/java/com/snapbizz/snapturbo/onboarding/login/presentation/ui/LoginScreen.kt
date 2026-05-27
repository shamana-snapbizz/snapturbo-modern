package com.snapbizz.snapturbo.onboarding.login.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.GenerationOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.VerifyOtpResult
import com.snapbizz.snapturbo.onboarding.login.presentation.ui.mobile.LoginCard
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LoginViewModel
import com.snapbizz.snapturbo.ui.components.loading.PosLoaderWithText
import com.snapbizz.snapturbo.ui.navigation.ScreenRoute
import com.snapbizz.snapturbo.ui.snackbar.SnackbarManager
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onAuthSuccess: (ScreenRoute) -> Unit, loginViewModel: LoginViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var mobileNumber by rememberSaveable { mutableStateOf("") }
    var otpCode by rememberSaveable { mutableStateOf("") }
    var isOtpScreenVisible by rememberSaveable { mutableStateOf(false) }
    var otpResendCountdown by rememberSaveable { mutableIntStateOf(10) }
    var canResendOtp by rememberSaveable { mutableStateOf(false) }
    var loadingMessage by remember { mutableStateOf("") }

    val generateOtpResult by loginViewModel.otpState.collectAsStateWithLifecycle()
    val verifyOtpResult by loginViewModel.verifyOtpState.collectAsStateWithLifecycle()

    val otpFocusRequester = remember { FocusRequester() }

    // ---------- OTP GENERATION RESULT ----------
    LaunchedEffect(generateOtpResult) {
        val generateOtpResult: NetworkResult<GenerationOtpResult> =
            generateOtpResult ?: return@LaunchedEffect
        when (generateOtpResult) {
            is NetworkResult.Success -> {
                if (generateOtpResult.data.status == "Success") {
                    isOtpScreenVisible = true
                    otpCode = ""
                    keyboardController?.show()
                } else {
                    SnackbarManager.show(generateOtpResult.data.status)
                }
            }

            is NetworkResult.Error -> SnackbarManager.show(generateOtpResult.message)

            else -> Unit
        }
    }

    // ---------- OTP VERIFICATION RESULT ----------
    LaunchedEffect(verifyOtpResult) {
        val verifyOtpResult: NetworkResult<VerifyOtpResult> =
            verifyOtpResult ?: return@LaunchedEffect
        when (verifyOtpResult) {
            is NetworkResult.Success -> {
                if (verifyOtpResult.data.isVerified) {
                    onAuthSuccess(ScreenRoute.Auth.Registration)
                } else {
                    otpCode = ""
                    otpFocusRequester.requestFocus()
                }
            }

            is NetworkResult.Error -> {
                otpCode = ""
//                otpFocusRequester.requestFocus()
            }

            else -> Unit
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LoginCard(
            loginViewModel = loginViewModel,
            mobileNumber = mobileNumber,
            otpCode = otpCode,
            isOtpScreenVisible = isOtpScreenVisible,
            otpResendCountdown = otpResendCountdown,
            canResendOtp = canResendOtp,
            onMobileChange = {
                if (it.length <= 10 && it.all(Char::isDigit)) {
                    mobileNumber = it
                }
            },
            onOtpChange = { otpCode = it },
            otpFocusRequester = otpFocusRequester,
            onPrimaryClick = {
                keyboardController?.hide()
                if (!isOtpScreenVisible) {
                    loadingMessage = "Generating OTP"
                    loginViewModel.generateOtp(mobileNumber.toLong())

                } else {
                    if (otpCode.length == 6 && otpCode.all(Char::isDigit)) {
                        loadingMessage = "Verifying OTP"
                        loginViewModel.verifyOtp(
                            otp = otpCode.toLong(), storeNumber = mobileNumber.toLong()
                        )
                    } else {
                        otpCode = ""/*    SnackbarManager.show("Invalid OTP",
                                position = SnackbarPosition.TOP)*/
                        keyboardController?.show()
                    }
                }
            },
            onResend = {
                otpCode = ""
                canResendOtp = false
                otpResendCountdown = 10
                loginViewModel.generateOtp(mobileNumber.toLong())
            })
        if (!canResendOtp) HandleOtpTimer(
            isOtpScreenVisible = isOtpScreenVisible,
            onCountdownTick = { otpResendCountdown = it },
            onCountdownFinished = { canResendOtp = true })
        //Loader
        if (generateOtpResult is NetworkResult.Loading || verifyOtpResult is NetworkResult.Loading) {
            LoadingOverlay(loadingMessage)
        }
    }
}

@Composable
private fun HandleOtpTimer(
    isOtpScreenVisible: Boolean, onCountdownTick: (Int) -> Unit, onCountdownFinished: () -> Unit
) {
    LaunchedEffect(isOtpScreenVisible) {
        if (!isOtpScreenVisible) return@LaunchedEffect
        for (remainingSeconds in 20 downTo 1) {
            onCountdownTick(remainingSeconds)
            delay(1_000)
        }
        onCountdownFinished()
    }
}

@Composable
private fun LoadingOverlay(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f)),
        contentAlignment = Alignment.Center
    ) {
        PosLoaderWithText(text)
    }
}
