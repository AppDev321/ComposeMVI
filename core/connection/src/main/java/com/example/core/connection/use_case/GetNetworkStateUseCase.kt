package com.example.core.connection.use_case

import com.example.core.connection.interfaces.ConnectionManager
import com.example.core.connection.model.NetworkAvailability
import javax.inject.Inject

class GetNetworkStateUseCase @Inject constructor(
    private val connectionManager: ConnectionManager
) {

    fun invoke(): NetworkAvailability {
        return connectionManager.getConnectionState()
    }
}
