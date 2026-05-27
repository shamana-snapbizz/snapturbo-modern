package com.snapbizz.snapturbo.commons.database

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase

object DbKeyProvider {
    private const val DB_KEY = "tiya_Priya"

    fun getDbKey(): String = DB_KEY

    fun getPassphrase(): ByteArray =
        SQLiteDatabase.getBytes(DB_KEY.toCharArray())
}