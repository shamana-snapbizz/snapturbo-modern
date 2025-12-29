package com.snapbizz.snapturbo.customers.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface CustomerApiService {
    @GET("customers")
    suspend fun getCustomers(): List<CustomerApiModel>

    @POST("customers")
    suspend fun addCustomer(@Body customer: CustomerApiModel)
}
