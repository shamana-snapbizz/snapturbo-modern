package com.snapbizz.snapturbo.onboarding.downloadsync.data.local

import android.content.Context
import androidx.room.Room
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.CustomerDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.InventoryDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SnapTurboDatabase {

        return Room.databaseBuilder(
            context,
            SnapTurboDatabase::class.java,
            "snapturbo.db"
        ).build()
    }

    @Provides
    fun provideProductDao(
        db: SnapTurboDatabase
    ): ProductDao = db.productDao()

    @Provides
    fun provideCustomerDao(
        db: SnapTurboDatabase
    ): CustomerDao = db.customerDao()

    @Provides
    fun provideInventoryDao(
        db: SnapTurboDatabase
    ): InventoryDao = db.inventoryDao()
}