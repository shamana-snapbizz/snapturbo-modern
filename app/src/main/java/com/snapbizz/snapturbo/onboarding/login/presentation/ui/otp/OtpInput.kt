package com.snapbizz.snapturbo.onboarding.login.presentation.ui.otp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snapbizz.snapturbo.ui.components.text.SnapText
import com.snapbizz.snapturbo.ui.components.text.SnapTextVariant
import com.snapbizz.snapturbo.ui.theme.SnapThemeConfig


@Composable
fun OtpCard1(
    mobile: String,
    onBack: () -> Unit,
    onVerifyOtp: (String) -> Unit
) {
    val firstOtpFocusRequester = remember { FocusRequester() }
    var otp by rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp)
                .widthIn(min = 380.dp, max = 420.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                SnapText(
                    text = "Verify OTP",
                    textVariant = SnapTextVariant.HEADING,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                // 🔒 Disabled mobile number
                OutlinedTextField(
                    value = mobile,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Mobile Number") }
                )

                Button(
                    onClick = { onVerifyOtp(otp) },
                    enabled = otp.length == 6,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Verify OTP")
                }

                TextButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Change Mobile Number")
                }
            }
        }
    }
}

@Composable
fun OtpInput(
    otp: String,
    length: Int = 6,
    onOtpChange: (String) -> Unit,
    firstFocusRequester: FocusRequester
) {
    val focusRequesters = remember { List(length) { FocusRequester() } }

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        repeat(length) { index ->

            OtpBox(
                value = otp.getOrNull(index)?.toString() ?: "",
                onValueChange = { newChar ->

                    // Handle deletion
                    if (newChar.isEmpty()) {

                        val chars = otp.padEnd(length, ' ').toCharArray()
                        chars[index] = ' '

                        onOtpChange(chars.concatToString().trimEnd())

                        if (index > 0) {
                            focusRequesters[index - 1].requestFocus()
                        }

                        return@OtpBox
                    }

                    // Handle digit entry
                    if (newChar.length == 1 && newChar[0].isDigit()) {

                        val chars = otp.padEnd(length, ' ').toCharArray()
                        chars[index] = newChar[0]

                        onOtpChange(chars.concatToString().trimEnd())

                        if (index < length - 1) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    }
                },
                onBackspace = {
                    val chars = otp.padEnd(length, ' ').toCharArray()

                    if (chars[index] != ' ') {
                        // ✅ Case 1: clear current box
                        chars[index] = ' '
                        onOtpChange(chars.concatToString().trimEnd())
                    } else if (index > 0) {
                        // ✅ Case 2: move left and clear
                        chars[index - 1] = ' '
                        onOtpChange(chars.concatToString().trimEnd())
                        focusRequesters[index - 1].requestFocus()
                    }
                },
                modifier = Modifier.focusRequester(
                    if (index == 0) firstFocusRequester else focusRequesters[index]
                )
            )
        }
    }
}


@Composable
fun OtpBox(
    value: String,
    onValueChange: (String) -> Unit,
    onBackspace: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .size(48.dp)
            .border(2.dp, SnapThemeConfig.Primary, RoundedCornerShape(8.dp))
            .onKeyEvent {
                if (it.type == KeyEventType.KeyDown && it.key == Key.Backspace) {
                    onBackspace()
                    true
                } else {
                    false
                }
            },
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        cursorBrush = SolidColor(Color.Black), // ✅ cursor visible
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        }
    )
}

