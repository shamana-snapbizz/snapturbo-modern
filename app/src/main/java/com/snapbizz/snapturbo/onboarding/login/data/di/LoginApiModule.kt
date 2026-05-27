package com.snapbizz.snapturbo.onboarding.login.data.di

import androidx.annotation.Px
import com.snapbizz.snapturbo.commons.di.PublicV2Api
import com.snapbizz.snapturbo.commons.di.PublicV3Api
import com.snapbizz.snapturbo.onboarding.login.data.remote.LoginApiService
import com.snapbizz.snapturbo.onboarding.login.data.remote.TokenApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object LoginApiModule {
        // ---------------- OTP API ----------------
        @Provides
        @Singleton
        fun provideLoginApiService(
            @PublicV2Api retrofit: Retrofit
        ): LoginApiService {
            return retrofit.create(LoginApiService::class.java)
        }
        @Provides
        @Singleton
        fun provideTokenApiService(
            @PublicV3Api retrofit: Retrofit
        ): TokenApiService =
            retrofit.create(TokenApiService::class.java)
    }