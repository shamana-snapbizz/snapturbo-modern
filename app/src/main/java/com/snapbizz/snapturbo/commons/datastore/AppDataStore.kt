package com.snapbizz.snapturbo.commons.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    /** SAVE DEVICE ID */
    suspend fun saveDeviceId(deviceId: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.DEVICE_ID] = deviceId
        }
    }

    /** OBSERVE DEVICE ID */
    val deviceIdFlow: Flow<String?> =
        dataStore.data.map { prefs ->
            prefs[PreferenceKeys.DEVICE_ID]
        }

    /** STORE NUMBER **/
    suspend fun saveStoreNumber(storeNumber: Long) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.STORE_NUMBER] = storeNumber
        }
    }

    val storeNumber: Flow<Long> =
        dataStore.data.map { prefs ->
            prefs[PreferenceKeys.STORE_NUMBER] ?: 0L
        }

    /** ACCESS TOKEN**/
    suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.ACCESS_TOKEN] = accessToken
        }
    }

    val accessToken: Flow<String?> =
        dataStore.data.map { prefs ->
            prefs[PreferenceKeys.ACCESS_TOKEN]
        }

    /** STORE ID **/
    suspend fun saveStoreId(storeId: Long) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.STORE_ID] = storeId
        }
    }

    val storeId: Flow<Long> =
        dataStore.data.map { prefs ->
            prefs[PreferenceKeys.STORE_ID] ?: 0L
        }

    /* ---------------- AUTH TOKEN ---------------- */
    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.AUTH_TOKEN] = authToken
        }
    }

    suspend fun clearAuthToken() {
        dataStore.edit { prefs ->
            prefs.remove(PreferenceKeys.AUTH_TOKEN)
        }
    }

    /* ---------- READ ---------- */
    val authToken: Flow<String?> =
        dataStore.data.map { prefs ->
            prefs[PreferenceKeys.AUTH_TOKEN]
        }

    suspend fun clearAll() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    /* ---------- VERIFY OTP RESPONSE ---------- */
    suspend fun saveVerifyOtpResponse(response: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.VERIFY_OTP_RESPONSE] = response
        }
    }

    val verifyOtpResponseFlow: Flow<String?> =
        dataStore.data.map { prefs ->
            prefs[PreferenceKeys.VERIFY_OTP_RESPONSE]
        }
}