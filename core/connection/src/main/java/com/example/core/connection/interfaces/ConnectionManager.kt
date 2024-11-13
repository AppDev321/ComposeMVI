package com.example.core.connection.interfaces

import com.example.core.connection.model.NetworkAvailability
import kotlinx.coroutines.flow.Flow

interface ConnectionManager {
    fun observeConnectionChanges(): Flow<NetworkAvailability>

    fun getConnectionState(): NetworkAvailability

    fun isConnectionAvailable (): Boolean
}