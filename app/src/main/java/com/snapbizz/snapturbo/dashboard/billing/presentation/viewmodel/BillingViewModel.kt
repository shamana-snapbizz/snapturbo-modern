package com.snapbizz.snapturbo.dashboard.billing.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.ProductEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.CustomerEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.repository.DownloadSyncRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class BillingViewModel @Inject constructor(
    private val repository: DownloadSyncRepository
) : ViewModel() {

    var products by mutableStateOf<List<ProductEntity>>(emptyList())
        private set

    var count by mutableStateOf(0)
        private set

    var customers by mutableStateOf<List<CustomerEntity>>(emptyList())
        private set

    var customerCount by mutableStateOf(0)
        private set

    init {
        viewModelScope.launch {

            count = repository.getProductCount()

            products = repository.getProducts()

            customerCount =
                repository.getCustomerCount()

            customers =
                repository.getCustomers()
        }
    }
}