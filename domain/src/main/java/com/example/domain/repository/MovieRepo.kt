package com.example.domain.repository


import com.example.models.MovieResponse
import com.example.response.APIResult
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    suspend fun getMoviesList(): Flow<APIResult<MovieResponse>>
}