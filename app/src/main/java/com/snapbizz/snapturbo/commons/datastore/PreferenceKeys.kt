package com.snapbizz.snapturbo.commons.datastore

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val DEVICE_ID = stringPreferencesKey("device_id")
    val AUTH_TOKEN = stringPreferencesKey("auth_token")
    val STORE_NUMBER = longPreferencesKey("store_number")
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val STORE_ID = longPreferencesKey("store_id")
    val VERIFY_OTP_RESPONSE = stringPreferencesKey("verify_otp_response")
}