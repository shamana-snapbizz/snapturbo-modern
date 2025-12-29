package com.snapbizz.snapturbo.commons.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snapbizz.snapturbo.customers.data.local.CustomerDao
import com.snapbizz.snapturbo.customers.data.local.CustomerEntity

@Database(
    entities = [CustomerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
}
