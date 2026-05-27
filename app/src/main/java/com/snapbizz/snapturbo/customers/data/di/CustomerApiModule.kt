package com.snapbizz.snapturbo.customers.data.di

import com.snapbizz.snapturbo.commons.di.AuthenticatedV2Api
import com.snapbizz.snapturbo.commons.di.PublicV3Api
import com.snapbizz.snapturbo.customers.data.remote.CustomerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CustomerApiModule {

    @Provides
    @Singleton
    fun provideCustomerApi(@PublicV3Api retrofit: Retrofit): CustomerApiService =
        retrofit.create(CustomerApiService::class.java)
}
