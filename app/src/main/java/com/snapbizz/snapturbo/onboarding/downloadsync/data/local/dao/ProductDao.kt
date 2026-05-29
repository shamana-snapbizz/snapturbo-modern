package com.snapbizz.snapturbo.onboarding.downloadsync.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.snapbizz.snapturbo.onboarding.downloadsync.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun clear()

    @Query("SELECT * FROM products LIMIT 50")
    suspend fun getProducts(): List<ProductEntity>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getCount(): Int

    @Transaction
    suspend fun replaceAll(
        products: List<ProductEntity>
    ) {
        clear()
        insertAll(products)
    }
}