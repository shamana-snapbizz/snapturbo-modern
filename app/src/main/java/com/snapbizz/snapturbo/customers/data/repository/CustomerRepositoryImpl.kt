package com.snapbizz.snapturbo.customers.data.repository

import com.snapbizz.snapturbo.commons.utils.NetworkResult
import com.snapbizz.snapturbo.customers.data.local.CustomerDao
import com.snapbizz.snapturbo.customers.data.local.CustomerEntity
import com.snapbizz.snapturbo.customers.data.remote.CustomerApiModel
import com.snapbizz.snapturbo.customers.data.remote.CustomerApiService
import com.snapbizz.snapturbo.customers.domain.model.Customer
import com.snapbizz.snapturbo.customers.domain.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val api: CustomerApiService,
    private val dao: CustomerDao
) : CustomerRepository {

    override fun getAllCustomers(): Flow<NetworkResult<List<Customer>>> =
        flow {
            emit(NetworkResult.Loading)

            try {
                // 🌐 Network call
                val response = api.getCustomers()

                // 🧠 Map API → Entity
                val entities = response.map {
                    CustomerEntity(it.id, it.name, it.email)
                }

                // 💾 DB insert (NOW on IO)
                dao.insertCustomers(entities)

                // 🎯 Emit domain models
                emit(
                    NetworkResult.Success(
                        response.map { Customer(it.id, it.name, it.email) }
                    )
                )

            } catch (e: Exception) {

                // 📦 Read from DB
                val cached = dao.getAllCustomers()
                    .map { Customer(it.id, it.name, it.email) }

                // ✅ EMIT cached data
                emit(NetworkResult.Success(cached))

                // or if you want error:
                // emit(NetworkResult.Error(e.localizedMessage ?: "Unknown Error"))
            }
        }
            .flowOn(Dispatchers.IO) //

    override suspend fun addCustomer(customer: Customer) =
        withContext(Dispatchers.IO){
        try {
            api.addCustomer(CustomerApiModel(customer.id, customer.name, customer.email))
        } catch (e: Exception) {
            dao.insertCustomers(listOf(CustomerEntity(customer.id, customer.name, customer.email)))

            // handle error
        }
    }
}

