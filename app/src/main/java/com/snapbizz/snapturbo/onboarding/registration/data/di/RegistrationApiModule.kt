package com.snapbizz.snapturbo.onboarding.registration.data.di

import com.snapbizz.snapturbo.commons.di.PublicV2Api
import com.snapbizz.snapturbo.onboarding.registration.data.remote.RegistrationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationApiModule {

    @Provides
    @Singleton
    fun provideRegistrationApiService(
        @PublicV2Api retrofit: Retrofit
    ): RegistrationApiService =
        retrofit.create(RegistrationApiService::class.java)
}
