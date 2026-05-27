package com.snapbizz.snapturbo.commons.network.interceptor

import android.util.Log
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.network.TokenProvider
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceInfoProvider
import com.snapbizz.snapturbo.onboarding.login.data.device.DeviceSession
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthHeaderInterceptor @Inject constructor(
    private val deviceSession: DeviceSession
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        Log.d("AUTH_DEBUG", "Auth interceptor running")
        deviceSession.accessToken?.let {
            request.header("access_token", it)
        }

        deviceSession.authToken?.let {
            request.header("authorization", "Bearer $it")
        }

        deviceSession.storeId?.let {
            request.header("store_id", it.toString())
        }

        deviceSession.deviceId?.let {
            request.header("device_id", it)
        }

        Timber.tag("AUTH_DEBUG").i(
            """
        access_token=${deviceSession.accessToken}
        authToken=${deviceSession.authToken}
        storeId=${deviceSession.storeId}
        deviceId=${deviceSession.deviceId}
        """.trimIndent()
        )
        Log.i(
            "AUTH_DEBUG",
            "access=${deviceSession.accessToken} auth=${deviceSession.authToken} store=${deviceSession.storeId} device=${deviceSession.deviceId}"
        )

        return chain.proceed(request.build())
    }
}
