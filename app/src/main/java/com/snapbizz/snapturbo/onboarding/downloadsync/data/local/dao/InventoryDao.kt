package com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.InventoryEntity

@Dao
interface InventoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<InventoryEntity>)

    @Query("DELETE FROM inventory")
    suspend fun clear()

    @Query("SELECT COUNT(*) FROM inventory")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replaceAll(
        inventory: List<InventoryEntity>
    ) {
        clear()
        insertAll(inventory)
    }
}