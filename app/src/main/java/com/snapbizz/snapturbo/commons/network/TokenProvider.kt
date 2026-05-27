package com.snapbizz.snapturbo.commons.network

interface TokenProvider {
    fun getAccessToken(): String?
    fun updateToken(token: String)
    fun clearToken()
}