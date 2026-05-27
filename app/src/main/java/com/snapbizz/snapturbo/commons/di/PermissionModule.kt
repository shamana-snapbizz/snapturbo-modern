package com.snapbizz.snapturbo.commons.di

import com.snapbizz.snapturbo.commons.permissions.data.repository.PermissionManagerImpl
import com.snapbizz.snapturbo.commons.permissions.domain.repository.PermissionManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionModule {

    @Binds
    abstract fun bindPermissionManager(
        impl: PermissionManagerImpl
    ): PermissionManager
}