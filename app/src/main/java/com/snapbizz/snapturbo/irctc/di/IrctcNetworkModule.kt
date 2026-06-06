package com.snapbizz.snapturbo.irctc.di

import com.snapbizz.snapturbo.commons.di.IrctcApi
import com.snapbizz.snapturbo.irctc.data.remote.InventoryApi
import com.snapbizz.snapturbo.irctc.data.remote.IrctcHeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IrctcNetworkModule {

    @Provides
    @Singleton
    @IrctcApi
    fun provideIrctcRetrofit(
        interceptor: IrctcHeaderInterceptor
    ): Retrofit {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client =
            OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(logging)
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
            .baseUrl("https://api-sb-test.snapbizz.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideInventoryApi(
        @IrctcApi retrofit: Retrofit
    ): InventoryApi {
        return retrofit.create(InventoryApi::class.java)
    }
}
