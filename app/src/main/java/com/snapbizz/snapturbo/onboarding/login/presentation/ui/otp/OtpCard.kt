package com.snapbizz.snapturbo.onboarding.login.presentation.ui.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import com.snapbizz.snapturbo.R
import com.snapbizz.snapturbo.commons.language.LocalAppResources
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.theme.SnapTextEmphasis

@Composable
 fun OtpContent(
    visible: Boolean,
    otp: String,
    onOtpChange: (String) -> Unit,
    focusRequester: FocusRequester,
    resendCountdown: Int,
    canResend: Boolean,
    onResend: () -> Unit
) {
    val appResources = LocalAppResources.current
    AnimatedVisibility(visible = visible) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SnapText(
                text = appResources.getString(R.string.enter_otp),
                emphasis = SnapTextEmphasis.TERTIARY
            )
            OtpInput(
                otp = otp,
                onOtpChange = onOtpChange,
                firstFocusRequester = focusRequester
            )

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (canResend) {
                    TextButton(onClick = onResend) {
                        Text(appResources.getString(R.string.resend_otp))
                    }
                } else {
                    SnapText(
                        text = "Resend in $resendCountdown sec",
                        emphasis = SnapTextEmphasis.TERTIARY
                    )
                }
            }
        }
    }
}
