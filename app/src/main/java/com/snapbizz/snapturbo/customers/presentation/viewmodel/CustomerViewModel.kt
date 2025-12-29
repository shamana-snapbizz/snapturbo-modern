package com.snapbizz.snapturbo.customers.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.customers.domain.model.Customer
import com.snapbizz.snapturbo.customers.domain.usecase.CustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val customerUseCase: CustomerUseCase
) : ViewModel() {

    private val _customersState = MutableStateFlow<NetworkResult<List<Customer>>>(NetworkResult.Loading)
    val customersState: StateFlow<NetworkResult<List<Customer>>> = _customersState

    fun fetchCustomers() {
        viewModelScope.launch(Dispatchers.IO) {
            customerUseCase.getAllCustomers().collect {
                _customersState.value = it
            }
        }
    }
}