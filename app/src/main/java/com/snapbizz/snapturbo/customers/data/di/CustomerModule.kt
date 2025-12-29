package com.snapbizz.snapturbo.customers.data.di

import com.snapbizz.snapturbo.customers.data.repository.CustomerRepositoryImpl
import com.snapbizz.snapturbo.customers.domain.repository.CustomerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CustomerModule {

    @Binds
    abstract fun bindCustomerRepository(
        impl: CustomerRepositoryImpl
    ): CustomerRepository
}
