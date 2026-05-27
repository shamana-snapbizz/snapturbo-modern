package com.snapbizz.snapturbo.onboarding.registration.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.registration.presentation.viewmodel.RegistrationViewModel

@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
    onDashBoardClick: () -> Unit,
) {
    val registrationState by registrationViewModel.registrationState.collectAsStateWithLifecycle()

    // Navigate away as soon as registration succeeds - no UI logic here.
    LaunchedEffect(registrationState) {
        if (registrationState is NetworkResult.Success) onDashBoardClick()
    }

    RegistrationCard(
        registrationViewModel = registrationViewModel,
        isLoading = registrationState is NetworkResult.Loading,
    )
}