package com.snapbizz.snapturbo.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapbizz.snapturbo.commons.permissions.domain.repository.PermissionManager
import com.snapbizz.snapturbo.onboarding.backup.domain.usecase.BackupUseCase
import com.snapbizz.snapturbo.commons.permissions.domain.usecase.CheckInitialPermissionsUseCase
import com.snapbizz.snapturbo.commons.utils.deviceid.DeviceIdProvider
import com.snapbizz.snapturbo.onboarding.backup.domain.usecase.StartupUseCase
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceInfoProvider
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceSession
import com.snapbizz.snapturbo.splash.presentation.ui.SplashUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkPermissions: CheckInitialPermissionsUseCase,
    private val permissionManager: PermissionManager,
    private val deviceIdProvider: DeviceIdProvider,
//    private val deviceInfoProvider: DeviceInfoProvider,
    private val deviceSession: DeviceSession
) : ViewModel() {

    private val _state = MutableStateFlow(SplashUiState())
    val state = _state.asStateFlow()

    private val _navigation = MutableSharedFlow<Unit>()
    val navigation = _navigation.asSharedFlow()

    fun requiredPermissions(): List<String> =
        permissionManager.requiredPermissions()
    private val _deviceId = MutableStateFlow<String?>(null)
    val deviceId: StateFlow<String?> = _deviceId

    init {
        viewModelScope.launch {
            _deviceId.value = deviceIdProvider.getDeviceId()
        }
    }
    fun onStart() {
        viewModelScope.launch {
            if (checkPermissions()) {
                _navigation.emit(Unit)
            } else {
                _state.update { it.copy(showPermissionDialog = true) }
            }
            viewModelScope.launch {
                // 1️⃣ Static values
                /*deviceSession.buildNo = deviceInfoProvider.buildNo
                deviceSession.modelNo = deviceInfoProvider.modelNo
                deviceSession.osType = deviceInfoProvider.osType
                deviceSession.osVersion = deviceInfoProvider.osVersion

                // 2️⃣ Runtime values (from DataStore)
                deviceSession.deviceId = deviceInfoProvider.getDeviceId()
                deviceSession.storeId = deviceInfoProvider.getStoreId()
                deviceSession.storeNumber = deviceInfoProvider.getStoreNumber()
                deviceSession.accessToken = deviceInfoProvider.getAccessToken()
                deviceSession.authToken = deviceInfoProvider.getAuthToken()*/
            }
        }
    }

    fun continueWithoutBlockingPermissions() {
        viewModelScope.launch {
            _navigation.emit(Unit)
        }
    }
}