package com.snapbizz.snapturbo.customers.domain.repository

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.customers.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getAllCustomers(): Flow<NetworkResult<List<Customer>>>
    suspend fun addCustomer(customer: Customer)
}
