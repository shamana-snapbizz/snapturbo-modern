package com.snapbizz.snapturbo.commons.utils

import android.annotation.SuppressLint
import android.os.Build

object AndroidVersionHelper {
    fun isTiramisuOrAbove(): Boolean = // Android 13 (API 33) and above
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    @SuppressLint("ObsoleteSdkInt")
    fun isMarshMallowOrBelow() : Boolean =   // Android 6.0 (API 23) to Android 12 (API 32)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    fun isNougatOrAbove() : Boolean =   // Android 6.0 (API 23) to Android 12 (API 32)
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
}