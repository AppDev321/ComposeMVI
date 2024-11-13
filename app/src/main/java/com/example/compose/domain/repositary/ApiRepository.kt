package com.example.compose.domain.repositary

import com.example.compose.utils.Resources
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getListings(): Flow<Resources<String>>
}