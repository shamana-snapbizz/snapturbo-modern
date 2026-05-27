package com.snapbizz.snapturbo.commons.di

import com.snapbizz.snapturbo.onboarding.backup.data.repository.BackupRepositoryImpl
import com.snapbizz.snapturbo.onboarding.backup.domain.respository.BackupRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BackupModule {

    @Binds
    abstract fun bindBackupRepo(
        impl: BackupRepositoryImpl
    ): BackupRepository
}
