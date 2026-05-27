package com.snapbizz.snapturbo.onboarding.login.presentation.ui.mobile

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.language.LocalAppResources
import com.snapbizz.snapturbo.onboarding.login.presentation.ui.otp.OtpContent
import com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel.LoginViewModel
import com.snapbizz.snapturbo.ui.components.SnapButton
import com.snapbizz.snapturbo.ui.components.SnapButtonVariant
import com.snapbizz.snapturbo.ui.components.text.SnapHighlightText
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.components.text.SnapTextComponent
import com.snapbizz.snapturbo.ui.components.text.SnapTextVariant
import com.snapbizz.snapturbo.ui.theme.Dimens.paddingSmall
import com.snapbizz.snapturbo.ui.theme.SnapColor
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig

@Composable
fun LoginCard(
    loginViewModel: LoginViewModel,
    mobileNumber: String,
    otpCode: String,
    isOtpScreenVisible: Boolean,
    otpResendCountdown: Int,
    canResendOtp: Boolean,
    onMobileChange: (String) -> Unit,
    onOtpChange: (String) -> Unit,
    otpFocusRequester: FocusRequester,
    onPrimaryClick: () -> Unit,
    onResend: () -> Unit
) {
    val appResources = LocalAppResources.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val deviceId by loginViewModel.deviceId.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(R.drawable.turbo_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.8f
        )
        Card(
            modifier = Modifier
                .padding(32.dp)
                .widthIn(380.dp, 420.dp)
                .animateContentSize()
                .background(color = SnapThemeConfig.Background),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.turbo_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentScale = ContentScale.Fit
                )
                SnapText(
                    text = appResources.getString(R.string.get_start),
                    textVariant = SnapTextVariant.HEADING,
                    isBold = true,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = SnapTextComponent.centerTextAlign
                )
                SnapText(
                    text = if (isOtpScreenVisible)
                        appResources.getString(R.string.sent_otp, mobileNumber)
                    else
                        appResources.getString(R.string.welcome_msg),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = SnapTextComponent.centerTextAlign
                )
                val isMobileError =
                    mobileNumber.isNotEmpty() && mobileNumber.length < 10
                val isButtonEnabled =
                    if (isOtpScreenVisible) otpCode.length == 6
                    else mobileNumber.length == 10
                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = onMobileChange,
                    label = { Text(appResources.getString(R.string.mobile_no)) },
                    enabled = !isOtpScreenVisible,
                    isError = isMobileError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions {
                        keyboardController?.hide()
                    }
                )
                OtpContent(
                    visible = isOtpScreenVisible,
                    otp = otpCode,
                    onOtpChange = onOtpChange,
                    focusRequester = otpFocusRequester,
                    resendCountdown = otpResendCountdown,
                    canResend = canResendOtp,
                    onResend = onResend
                )
                SnapButton(
                    onClick = onPrimaryClick,
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = false,
                    enabled = isButtonEnabled,
                    text =
                        if (isOtpScreenVisible)
                            appResources.getString(R.string.submit)
                        else
                            appResources.getString(R.string.get_otp),
                    variant = SnapButtonVariant.PRIMARY,
                    textColorInt = SnapColor.textColor.hashCode(),
                    backgroundColorInt = SnapColor.primaryColor.hashCode()
                )
            }
            Text(
                appResources.getString(R.string.device_id) + (deviceId ?: ""),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))
            SnapHighlightText(
                text = "${BuildConfig.APP_VERSION_PREFIX} : ${BuildConfig.VERSION_NAME}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingSmall),
                highlightRange = 0..10,
                align = SnapTextComponent.centerTextAlign
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}
