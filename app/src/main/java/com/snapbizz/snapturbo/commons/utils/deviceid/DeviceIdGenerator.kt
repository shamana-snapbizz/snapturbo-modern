package com.snapbizz.snapturbo.commons.utils.deviceid

import kotlin.math.absoluteValue

object DeviceIdGenerator {
    /**
     * Converts ANDROID_ID into a numeric-only unique ID
     */
    fun generateNumericId(androidId: String): String {
        val hash = androidId.hashCode().toLong().absoluteValue
        return hash.toString().padStart(10, '0')
    }
}