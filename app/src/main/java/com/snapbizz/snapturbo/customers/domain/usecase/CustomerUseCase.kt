package com.snapbizz.snapturbo.customers.domain.usecase

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.customers.data.repository.CustomerRepositoryImpl
import com.snapbizz.snapturbo.customers.domain.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerUseCase @Inject constructor(
    private val repository: CustomerRepositoryImpl
) {
    suspend fun getAllCustomers(): Flow<NetworkResult<List<Customer>>> {
        return repository.getAllCustomers()
    }

    suspend fun addCustomer(customer: Customer) {
        repository.addCustomer(customer)
    }
}