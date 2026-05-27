package com.snapbizz.snapturbo.onboarding.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceSession
import com.snapbizz.snapturbo.onboarding.login.domain.model.GenerationOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.model.VerifyOtpResult
import com.snapbizz.snapturbo.onboarding.login.domain.usecase.GenerateOtpUseCase
import com.snapbizz.snapturbo.onboarding.login.domain.usecase.VerifyOtpUseCase
import com.snapbizz.snapturbo.ui.snackbar.SnackbarManager
import com.snapbizz.snapturbo.ui.snackbar.SnackbarPosition
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val generateOtpUseCase: GenerateOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    val appDataStore: AppDataStore,
    private val deviceSession: DeviceSession
) : ViewModel() {

    private val _otpState = MutableStateFlow<NetworkResult<GenerationOtpResult>?>(null)
    val otpState: StateFlow<NetworkResult<GenerationOtpResult>?> = _otpState

    private val _verifyOtpState = MutableStateFlow<NetworkResult<VerifyOtpResult>?>(null)
    val verifyOtpState: StateFlow<NetworkResult<VerifyOtpResult>?> = _verifyOtpState

    val deviceId = appDataStore.deviceIdFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun generateOtp(storePhone: Long) {
        viewModelScope.launch {
            _otpState.value = NetworkResult.Loading

            when (val result = generateOtpUseCase(storePhone)) {

                is NetworkResult.Success -> {
                    _otpState.value = result

                    result.data.status.let {
                        SnackbarManager.show(
                            message = it
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _otpState.value = result
                    SnackbarManager.show(
                        message = result.message,
                        position = SnackbarPosition.CENTER
                    )
                }

                is NetworkResult.Loading -> {
                    _otpState.value = NetworkResult.Loading
                }
            }
        }
    }

    fun verifyOtp(otp: Long, storeNumber: Long) {
        _verifyOtpState.value = NetworkResult.Loading
        viewModelScope.launch {
            appDataStore.saveStoreNumber(storeNumber)
            when (val result = verifyOtpUseCase(otp)) {

                is NetworkResult.Success -> {

                    if (result.data.isVerified) {
                        saveRegistrationDetails(result.data)
                            deviceSession.deviceId = appDataStore.deviceIdFlow.firstOrNull()
                            deviceSession.storeId = appDataStore.storeId.firstOrNull()
                            deviceSession.accessToken = appDataStore.accessToken.firstOrNull()
                            deviceSession.authToken = appDataStore.authToken.firstOrNull()

//                        _verifyOtpState.value = result.data.message
                    } else {
                        // ❌ OTP invalid → stay here
                        _verifyOtpState.value = result

                      /*  SnackbarManager.show(
                            message = result.data.message,
                            position = SnackbarPosition.CENTER
                        )*/

                    }
                    _verifyOtpState.value = result
                }

                is NetworkResult.Error -> {
                    _verifyOtpState.value = result

                    SnackbarManager.show(
                        message = result.message
                    )
                }

                is NetworkResult.Loading -> {
                    _verifyOtpState.value = NetworkResult.Loading
                }
            }
        }
    }

    private suspend fun saveRegistrationDetails(data: VerifyOtpResult) {
        data.run {
            accessToken?.let {
                appDataStore.saveAccessToken(it)

                SnackbarManager.show(
                    message = accessToken
                )
            }
            store?.let {
                appDataStore.saveStoreId(it.storeId)
                SnackbarManager.show(
                    message = it.storeId.toString()
                )
            }
            authToken.let {
                appDataStore.saveAuthToken(it)
                SnackbarManager.show(
                    message = authToken
                )
            }

            val responseJson = Gson().toJson(this)

            Log.e("OTP_DEBUG", "Before saveVerifyOtpResponse")

            appDataStore.saveVerifyOtpResponse(responseJson)
            Log.e("OTP_DEBUG", "After saveVerifyOtpResponse")
        }
    }
}

