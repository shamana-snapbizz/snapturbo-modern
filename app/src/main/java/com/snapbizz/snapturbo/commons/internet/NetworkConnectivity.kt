package com.snapbizz.snapturbo.commons.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NetworkConnectivityImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkMonitor {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val status: StateFlow<NetworkStatus> = callbackFlow {

        // 🔥 Emit real initial state
        trySend(currentStatus())

        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                trySend(NetworkStatus.Available)
            }

            override fun onLost(network: Network) {
                trySend(NetworkStatus.Unavailable)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                capabilities: NetworkCapabilities
            ) {
                val validated =
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)

                trySend(
                    if (validated)
                        NetworkStatus.Available
                    else
                        NetworkStatus.Unavailable
                )
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
        .distinctUntilChanged()
        .stateIn(
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Default),
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NetworkStatus.CheckInternet
        )
    /**
     * 🔑 REQUIRED for OkHttp Interceptor (SYNC check)
     */
    fun isConnected(): Boolean {
        return currentStatus() == NetworkStatus.Available
    }

    private fun currentStatus(): NetworkStatus {
        val network = connectivityManager.activeNetwork
            ?: return NetworkStatus.Unavailable

        val caps = connectivityManager.getNetworkCapabilities(network)
            ?: return NetworkStatus.Unavailable

        return if (
            caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        ) {
            NetworkStatus.Available
        } else {
            NetworkStatus.Unavailable
        }
    }
}


