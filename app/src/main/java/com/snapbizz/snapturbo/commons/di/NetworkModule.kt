/*package com.snapbizz.snapturbo.commons.di

import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.internet.NetworkConnectivityImpl
import com.snapbizz.snapturbo.commons.internet.NetworkMonitor
import com.snapbizz.snapturbo.commons.network.TokenProvider
import com.snapbizz.snapturbo.commons.network.TokenProviderImpl
import com.snapbizz.snapturbo.commons.network.authenticator.TokenRefresher
import com.snapbizz.snapturbo.commons.network.interceptor.AuthHeaderInterceptor
import com.snapbizz.snapturbo.commons.network.interceptor.HeaderLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 40L

    // ----------------------------------------------------------------
    // PUBLIC OKHTTP CLIENT (NO AUTH)
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @PublicClient
    fun providePublicOkHttpClient(
        networkInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(logging)
            .build()
    }

    // ----------------------------------------------------------------
    // AUTHENTICATED OKHTTP CLIENT
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @AuthenticatedClient
    fun provideAuthenticatedOkHttpClient(
        @PublicClient baseClient: OkHttpClient,
        headerLoggingInterceptor: HeaderLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenRefresher: TokenRefresher
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

        return baseClient.newBuilder()
            .addInterceptor(authHeaderInterceptor)    // adds headers
            .addInterceptor(headerLoggingInterceptor) // logs headers
            .addInterceptor(logging)                  // 3️⃣ OkHttp BODY logs
            .authenticator(tokenRefresher)             // refresh token
            .build()
    }

    // ----------------------------------------------------------------
    // TOKEN PROVIDER
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    fun provideTokenProvider(
        appDataStore: AppDataStore
    ): TokenProvider = TokenProviderImpl(appDataStore)

    // ----------------------------------------------------------------
    // RETROFIT – V2
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @PublicV2Api
    fun providePublicV2Retrofit(
        @PublicClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL_V2)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @AuthenticatedV2Api
    fun provideAuthenticatedV2Retrofit(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL_V2)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // ----------------------------------------------------------------
    // RETROFIT – V3
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @PublicV3Api
    fun providePublicV3Retrofit(
        @PublicClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_V3)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    @AuthenticatedV3Api
    fun provideAuthenticatedV3Retrofit(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_V3)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // ----------------------------------------------------------------
    // NETWORK MONITOR
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    fun provideNetworkMonitor(
        impl: NetworkConnectivityImpl
    ): NetworkMonitor = impl
}

/**
 * Network availability check
 */
@Singleton
class NetworkConnectionInterceptor @Inject constructor(
    private val networkMonitor: NetworkConnectivityImpl
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkMonitor.isConnected()) {
            throw IOException("No Internet Connection")
        }
        return chain.proceed(chain.request())
    }
}
*/


package com.snapbizz.snapturbo.commons.di

import com.google.gson.GsonBuilder
import com.snapbizz.snapturbo.BuildConfig
import com.snapbizz.snapturbo.commons.datastore.AppDataStore
import com.snapbizz.snapturbo.commons.internet.NetworkConnectivityImpl
import com.snapbizz.snapturbo.commons.internet.NetworkMonitor
import com.snapbizz.snapturbo.commons.network.TokenProvider
import com.snapbizz.snapturbo.commons.network.TokenProviderImpl
import com.snapbizz.snapturbo.commons.network.authenticator.TokenRefresher
import com.snapbizz.snapturbo.commons.network.interceptor.AuthHeaderInterceptor
import com.snapbizz.snapturbo.commons.network.interceptor.HeaderLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 40L

    private val gson = GsonBuilder()
        .serializeNulls()
        .create()

    // ----------------------------------------------------------------
    // PUBLIC OKHTTP CLIENT (NO AUTH)
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @PublicClient
    fun providePublicOkHttpClient(
        networkInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(logging)
            .build()
    }

    // ----------------------------------------------------------------
    // LEGACY V2 CLIENT (NO BEARER AUTH)
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @AuthenticatedV2Client
    fun provideAuthenticatedV2OkHttpClient(
        networkInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(logging)

            // IMPORTANT:
            // NO AuthHeaderInterceptor
            // NO HeaderLoggingInterceptor
            // NO TokenRefresher

            .build()
    }

    // ----------------------------------------------------------------
    // AUTHENTICATED OKHTTP CLIENT (V3)
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @AuthenticatedClient
    fun provideAuthenticatedOkHttpClient(
        @PublicClient baseClient: OkHttpClient,
        headerLoggingInterceptor: HeaderLoggingInterceptor,
        authHeaderInterceptor: AuthHeaderInterceptor,
        tokenRefresher: TokenRefresher
    ): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
        }

        return baseClient.newBuilder()
            .addInterceptor(authHeaderInterceptor)
            .addInterceptor(headerLoggingInterceptor)
            .addInterceptor(logging)
            .authenticator(tokenRefresher)
            .build()
    }

    // ----------------------------------------------------------------
    // TOKEN PROVIDER
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    fun provideTokenProvider(
        appDataStore: AppDataStore
    ): TokenProvider = TokenProviderImpl(appDataStore)

    // ----------------------------------------------------------------
    // RETROFIT – PUBLIC V2
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @PublicV2Api
    fun providePublicV2Retrofit(
        @PublicClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL_V2)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // ----------------------------------------------------------------
    // RETROFIT – AUTHENTICATED V2 (LEGACY STYLE)
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @AuthenticatedV2Api
    fun provideAuthenticatedV2Retrofit(
        @AuthenticatedV2Client okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL_V2)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // ----------------------------------------------------------------
    // RETROFIT – PUBLIC V3
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @PublicV3Api
    fun providePublicV3Retrofit(
        @PublicClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_V3)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // ----------------------------------------------------------------
    // RETROFIT – AUTHENTICATED V3
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    @AuthenticatedV3Api
    fun provideAuthenticatedV3Retrofit(
        @AuthenticatedClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_V3)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    // ----------------------------------------------------------------
    // NETWORK MONITOR
    // ----------------------------------------------------------------
    @Provides
    @Singleton
    fun provideNetworkMonitor(
        impl: NetworkConnectivityImpl
    ): NetworkMonitor = impl
}

/**
 * Network availability check
 */
@Singleton
class NetworkConnectionInterceptor @Inject constructor(
    private val networkMonitor: NetworkConnectivityImpl
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!networkMonitor.isConnected()) {
            throw IOException("No Internet Connection")
        }

        return chain.proceed(chain.request())
    }
}