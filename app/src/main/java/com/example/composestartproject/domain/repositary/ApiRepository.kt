package com.example.composestartproject.domain.repositary

import com.example.composestartproject.utils.Resources
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getListings(): Flow<Resources<String>>
}