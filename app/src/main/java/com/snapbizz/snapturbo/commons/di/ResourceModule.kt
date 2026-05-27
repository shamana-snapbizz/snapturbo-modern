package com.snapbizz.snapturbo.commons.di

import android.content.Context
import com.snapbizz.snapturbo.commons.utils.resources.AppResources
import com.snapbizz.snapturbo.commons.utils.resources.AppResourcesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {
    @Provides
    @Singleton
    fun provideAppResources(
        @ApplicationContext context : Context
    ) : AppResources = AppResourcesImpl(context)
}