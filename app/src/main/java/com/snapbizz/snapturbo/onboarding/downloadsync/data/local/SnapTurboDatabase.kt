package com.snapbizz.snapturbo.onboarding.downloadsync.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.CustomerDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.InventoryDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao.ProductDao
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.CustomerEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.InventoryEntity
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.ProductEntity
import com.snapbizz.snapturbo.onboarding.login.data.local.dao.UserDao
import com.snapbizz.snapturbo.onboarding.login.data.local.entity.UserEntity

@Database(
    entities = [
        ProductEntity::class,
        CustomerEntity::class,
        InventoryEntity::class,
        UserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class SnapTurboDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun customerDao(): CustomerDao

    abstract fun inventoryDao(): InventoryDao

    abstract fun userDao(): UserDao
}