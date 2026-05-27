package com.snapbizz.snapturbo.commons.internet

import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val status : StateFlow<NetworkStatus>
}

/*
@EntryPoint
@InstallIn(SingletonComponent::class)
interface NetworkEntryPoint {
    fun networkMonitor(): Netwo
}*/
