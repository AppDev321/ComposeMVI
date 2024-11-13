package com.example.core.connection.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.example.core.connection.extension.getNetworkAvailability
import com.example.core.connection.interfaces.ConnectionManager
import com.example.core.connection.model.NetworkAvailability
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

internal class ConnectionManagerImpl(context: Context): ConnectionManager {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun getConnectionState(): NetworkAvailability =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            .getNetworkAvailability()

    override fun isConnectionAvailable(): Boolean {
        return getConnectionState() == NetworkAvailability.AVAILABLE
    }

    override fun observeConnectionChanges(): Flow<NetworkAvailability> {
        return callbackFlow {
            send(getConnectionState())

            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(NetworkAvailability.AVAILABLE) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(NetworkAvailability.LOSING) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(NetworkAvailability.LOST) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(NetworkAvailability.UNAVAILABLE) }
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }

}