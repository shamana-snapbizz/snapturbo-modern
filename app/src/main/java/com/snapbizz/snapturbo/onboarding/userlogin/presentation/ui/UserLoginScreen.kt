package com.snapbizz.snapturbo.onboarding.userlogin.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val AxisPink = Color(0xFFB01257)

@Composable
fun UserLoginScreen(
    onLoginClick: (String, String) -> Unit
) {

    var username by remember {
        mutableStateOf("admin")
    }

    var password by remember {
        mutableStateOf("admin")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .background(AxisPink),
                    contentAlignment = Alignment.CenterStart
                ) {

                    Text(
                        text = "AXIS BANK",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(40.dp)
                )

                Text(
                    text = "DIGITAL DUKAAN",
                    color = AxisPink,
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(
                    modifier = Modifier.height(48.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    Text(
                        text = "Enter Username"
                    )

                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Text(
                        text = "Enter Password"
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {
                            onLoginClick(
                                username,
                                password
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AxisPink
                        )
                    ) {

                        Text(
                            text = "Login",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}