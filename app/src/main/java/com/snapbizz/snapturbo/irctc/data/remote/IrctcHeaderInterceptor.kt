package com.snapbizz.snapturbo.irctc.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class IrctcHeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "eyJhbGciOiJIUzUxMiJ9.eyJhY2Nlc3NfdG9rZW4iOiIzZ3EzZm9sMTkzNjlucGxxbTRsbjNjaGZsM2Fob2lsZm9jaGNjY2tudG5vMzliNGVmNHQ4dDMyaTVtbWQxOHJ1cG5mMiIsInN1YiI6IjkzNDk1XzFmZTlkMzYxM2ExZTVmYTciLCJleHAiOjE3Nzk0NDU2NjksImlhdCI6MTc3ODc1NDQ2OX0.pUzQBbADiq3W78GujqFD4QhIuuKdtLjtJyVmlhGQKiKPXT5fyUzNKH4dtd8unt9JSDOF59SogXeXZb95xGJTeg"
            )
            .addHeader("Store_id", "93555")
            .addHeader("device_id", "WIN1fe9d3613a1e5fa7")
            .addHeader(
                "access_token",
                "3gq3fol19369nplqm4ln3chfl3ahoilfochccckntno39b4ef4t8t32i5mmd18rupnf2"
            )
            .build()

        return chain.proceed(request)
    }
}
