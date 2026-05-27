package com.snapbizz.snapturbo.onboarding.login.data.di

import com.snapbizz.snapturbo.onboarding.login.data.repository.LoginRepositoryImpl
import com.snapbizz.snapturbo.onboarding.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun bindOtpRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository
}