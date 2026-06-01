package com.snapbizz.snapturbo.onboarding.downloadsync.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.CustomerDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.InventoryDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.ProductDao
import com.snapbizz.snapturbo.onboarding.login.data.local.dao.UserDao
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
        )
            .fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
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

    @Provides
    fun provideUserDao(
        db: SnapTurboDatabase
    ): UserDao = db.userDao()
}