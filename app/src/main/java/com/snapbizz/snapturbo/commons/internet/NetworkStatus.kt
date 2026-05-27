package com.snapbizz.snapturbo.commons.internet

sealed class NetworkStatus {
    object CheckInternet : NetworkStatus()
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}