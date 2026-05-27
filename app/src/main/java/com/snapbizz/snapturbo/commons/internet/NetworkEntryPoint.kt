package com.snapbizz.snapturbo.commons.internet

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface NetworkEntryPoint {
    fun networkConnectivity(): NetworkConnectivityImpl
}