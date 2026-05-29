package com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.CustomerEntity

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(customers: List<CustomerEntity>)

    @Query("DELETE FROM customers")
    suspend fun clear()

    @Query("SELECT * FROM customers LIMIT 50")
    suspend fun getCustomers(): List<CustomerEntity>

    @Query("SELECT COUNT(*) FROM customers")
    suspend fun getCustomerCount(): Int

    @Transaction
    suspend fun replaceAll(
        customers: List<CustomerEntity>
    ) {
        clear()
        insertAll(customers)
    }
}