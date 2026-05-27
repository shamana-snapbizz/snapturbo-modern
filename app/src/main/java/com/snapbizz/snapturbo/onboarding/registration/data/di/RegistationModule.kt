package com.snapbizz.snapturbo.onboarding.registration.data.di

import com.snapbizz.snapturbo.onboarding.login.domain.repository.LoginRepository
import com.snapbizz.snapturbo.onboarding.registration.data.repository.RegistrationRepositoryImpl
import com.snapbizz.snapturbo.onboarding.registration.domain.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class RegistationModule {

    @Binds
    abstract fun bindRegistrationRepository(
        impl: RegistrationRepositoryImpl
    ): RegistrationRepository

}
