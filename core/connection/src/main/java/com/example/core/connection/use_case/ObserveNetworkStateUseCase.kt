package com.example.core.connection.use_case

import com.example.core.connection.interfaces.ConnectionManager
import com.example.core.connection.model.NetworkAvailability
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveNetworkStateUseCase @Inject constructor(
    private val connectionManager: ConnectionManager
) {

    fun invoke(): Flow<NetworkAvailability> {
        return connectionManager.observeConnectionChanges()
    }
}
