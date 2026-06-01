package com.snapbizz.snapturbo.onboarding.login.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snapbizz.snapturbo.onboarding.login.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query(
        """
        SELECT * FROM users
        WHERE username = :username
        AND password = :password
        LIMIT 1
        """
    )
    suspend fun login(
        username: String,
        password: String
    ): UserEntity?

    @Query(
        """
        SELECT * FROM users
        WHERE username = 'admin'
        LIMIT 1
        """
    )
    suspend fun getAdmin(): UserEntity?
}
