package com.snapbizz.snapturbo.onboarding.login.data.device

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DeviceInfoModule {

    @Binds
    @Singleton
    abstract fun bindDeviceInfoProvider(
        impl: DeviceInfoProviderImpl
    ): DeviceInfoProvider
}