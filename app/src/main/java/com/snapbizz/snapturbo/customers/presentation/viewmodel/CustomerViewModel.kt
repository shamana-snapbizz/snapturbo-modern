package com.snapbizz.snapturbo.customers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.internet.NetworkMonitor
import com.snapbizz.snapturbo.commons.internet.NetworkStatus
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.commons.utils.deviceid.DeviceIdProvider
import com.snapbizz.snapturbo.customers.domain.model.Customer
import com.snapbizz.snapturbo.customers.domain.usecase.CustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.apply

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customerUseCase: CustomerUseCase,
    networkMonitor: NetworkMonitor,
    private val appDataStore: AppDataStore,
    private val deviceIdProvider: DeviceIdProvider
) : ViewModel() {

    // ✅ Network status
    val networkStatus: StateFlow<NetworkStatus> = networkMonitor.status

  /*  // ✅ Device ID exposed properly
    val deviceId: StateFlow<String?> =
        appDataStore.deviceIdFlow
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )
*/
    private val _deviceId = MutableStateFlow<String?>(null)
    val deviceId: StateFlow<String?> = _deviceId

    init {
        viewModelScope.launch {
            _deviceId.value = deviceIdProvider.getDeviceId()
        }
    }
    private val _customersState = MutableStateFlow<NetworkResult<List<Customer>>>(NetworkResult.Loading)
    val customersState: StateFlow<NetworkResult<List<Customer>>> = _customersState



    fun fetchCustomers() {
        viewModelScope.launch(Dispatchers.IO) {
            val customer = Customer(  id = 22, name = "navii", email = "Nav@gmail.com")
            customerUseCase.addCustomer(customer)
            customerUseCase.getAllCustomers().collect {
                _customersState.value = it
            }
        }
    }



    fun saveDeviceId(deviceId: String) {4
        viewModelScope.launch {
            appDataStore.saveDeviceId(deviceId)
        }
    }
}
