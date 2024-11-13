package com.example.core.connection.use_case

import com.example.core.connection.interfaces.ConnectionManager
import javax.inject.Inject


class IsNetworkAvailableUseCase @Inject constructor(
    private val connectionManager: ConnectionManager
) {

    fun invoke(): Boolean {
        return connectionManager.isConnectionAvailable()
    }
}
