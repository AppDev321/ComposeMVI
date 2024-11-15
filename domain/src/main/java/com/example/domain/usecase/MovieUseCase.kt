package com.example.domain.usecase

import com.example.models.MovieResponse
import com.example.domain.repository.MovieRepo
import com.example.response.APIResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepo: MovieRepo
) {
    suspend fun getMovies(): Flow<APIResult<MovieResponse>> {
        return movieRepo.getMoviesList()
    }
}