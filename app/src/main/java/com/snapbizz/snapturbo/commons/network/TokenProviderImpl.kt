package com.snapbizz.snapturbo.commons.network

import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProviderImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : TokenProvider {

    @Volatile
    private var cachedToken: String? = null

    override fun getAccessToken(): String? {
        return cachedToken
    }

    override fun updateToken(token: String) {
        cachedToken = token
    }

    override fun clearToken() {
        cachedToken = null
    }

    suspend fun loadTokenFromStore() {
        cachedToken = appDataStore.accessToken.first()
    }
}
