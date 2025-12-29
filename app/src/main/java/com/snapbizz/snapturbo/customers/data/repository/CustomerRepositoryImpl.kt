package com.snapbizz.snapturbo.customers.data.repository

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.customers.data.local.CustomerDao
import com.snapbizz.snapturbo.customers.data.local.CustomerEntity
import com.snapbizz.snapturbo.customers.data.remote.CustomerApiModel
import com.snapbizz.snapturbo.customers.data.remote.CustomerApiService
import com.snapbizz.snapturbo.customers.domain.model.Customer
import com.snapbizz.snapturbo.customers.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val api: CustomerApiService,
    private val dao: CustomerDao
) : CustomerRepository {

    override fun getAllCustomers(): Flow<NetworkResult<List<Customer>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = api.getCustomers()
            val customers = response.map { Customer(it.id, it.name, it.email) }
            dao.insertCustomers(response.map { CustomerEntity(it.id, it.name, it.email) })
            emit(NetworkResult.Success(customers))
        } catch (e: Exception) {
            val cached = dao.getAllCustomers().map { Customer(it.id, it.name, it.email) }
            emit(NetworkResult.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    override suspend fun addCustomer(customer: Customer) {
        try {
            api.addCustomer(CustomerApiModel(customer.id, customer.name, customer.email))
            dao.insertCustomers(listOf(CustomerEntity(customer.id, customer.name, customer.email)))
        } catch (e: Exception) {
            // handle error
        }
    }
}
