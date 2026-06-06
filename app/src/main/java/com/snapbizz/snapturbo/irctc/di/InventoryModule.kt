package com.snapbizz.snapturbo.irctc.di

import com.snapbizz.snapturbo.irctc.data.repository.InventoryRepositoryImpl
import com.snapbizz.snapturbo.irctc.domain.repository.InventoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InventoryModule {

    @Binds
    abstract fun bindInventoryRepository(
        impl: InventoryRepositoryImpl
    ): InventoryRepository
}