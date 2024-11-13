package com.example.core.connection.extension

import android.net.NetworkCapabilities
import com.example.core.connection.model.NetworkAvailability

fun NetworkCapabilities?.getNetworkAvailability(): NetworkAvailability = when {
    this == null -> NetworkAvailability.UNAVAILABLE
    hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) &&
            (hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> NetworkAvailability.AVAILABLE

    else -> NetworkAvailability.UNAVAILABLE
}