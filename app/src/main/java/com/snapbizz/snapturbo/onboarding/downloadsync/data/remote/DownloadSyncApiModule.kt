package com.snapbizz.snapturbo.onboarding.downloadsync.data.remote

import com.snapbizz.snapturbo.commons.di.PublicV2Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DownloadSyncApiModule {

    @Provides
    @Singleton
    fun provideDownloadSyncApiService(
        @PublicV2Api retrofit: Retrofit
    ): DownloadSyncApiService {

        return retrofit.create(
            DownloadSyncApiService::class.java
        )
    }
}