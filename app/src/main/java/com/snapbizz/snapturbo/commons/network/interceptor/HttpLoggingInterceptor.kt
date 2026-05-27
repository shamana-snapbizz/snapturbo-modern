package com.snapbizz.snapturbo.commons.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeaderLoggingInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Timber.tag("HTTP_HEADERS").d(
            """
            ┌──────────── HTTP REQUEST ────────────
            │ ${request.method} ${request.url}
            │ Headers:
            │ ${request.headers}
            └──────────────────────────────────────
            """.trimIndent()
        )

        return chain.proceed(request)
    }
}
