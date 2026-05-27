package com.snapbizz.snapturbo.commons.di

import com.snapbizz.snapturbo.onboarding.backup.data.repository.StartupRepositoryImpl
import com.snapbizz.snapturbo.onboarding.backup.domain.respository.StartupRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StartupModule {

    @Binds
    abstract fun bindStartupRepository(
        impl: StartupRepositoryImpl
    ): StartupRepository
}
