package com.snapbizz.snapturbo.commons.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE customers ADD COLUMN phone TEXT"
        )
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {

        db.execSQL("""
            CREATE TABLE customers_new (
                id INTEGER NOT NULL PRIMARY KEY,
                name TEXT NOT NULL,
                email TEXT NOT NULL,
                phone TEXT
            )
        """)

        db.execSQL("""
            INSERT INTO customers_new (id, name, email, phone)
            SELECT id, name, email, phone FROM customers
        """)

        db.execSQL("DROP TABLE customers")
        db.execSQL("ALTER TABLE customers_new RENAME TO customers")
    }
}
