package com.snapbizz.snapturbo.onboarding.userlogin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapbizz.snapturbo.onboarding.userlogin.presentation.viewmodel.UserLoginViewModel

@Composable
fun UserLoginRoute(
    onSuccess: () -> Unit,
    viewModel: UserLoginViewModel = hiltViewModel()
) {

    val success by
    viewModel.loginSuccess.collectAsStateWithLifecycle()

    LaunchedEffect(success) {
        if (success) onSuccess()
    }

    UserLoginScreen(
        onLoginClick = { user, pass ->
            viewModel.login(user, pass)
        }
    )
}
