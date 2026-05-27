package com.snapbizz.snapturbo.commons.di

import android.content.Context
import androidx.room.Room
import com.snapbizz.snapturbo.commons.database.AppDatabase
import com.snapbizz.snapturbo.commons.database.DbKeyProvider
import com.snapbizz.snapturbo.customers.data.local.CustomerDao
import com.snapbizz.snapturbo.commons.database.MIGRATION_1_2
import com.snapbizz.snapturbo.commons.database.MIGRATION_2_3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        SQLiteDatabase.loadLibs(context)

        val passphrase = SQLiteDatabase.getBytes(
            DbKeyProvider.getDbKey().toCharArray()
        )
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "snapturbo_Naveendb"
        )
            .openHelperFactory(factory) // 🔐 ENCRYPTION ENABLED
            .addMigrations(
                MIGRATION_1_2,
                MIGRATION_2_3
            )
            .build()
    }

    @Provides
    fun provideCustomerDao(db: AppDatabase): CustomerDao =
        db.customerDao()
}


/*@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "snapturbo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(db: AppDatabase) = db.customerDao()


}*/
