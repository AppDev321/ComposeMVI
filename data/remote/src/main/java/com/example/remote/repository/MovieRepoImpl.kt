package com.example.remote.repository

import com.example.compose.retrofit.common.ErrorHandler
import com.example.domain.repository.MovieRepo
import com.example.models.MovieResponse
import com.example.remote.service.ApiServiceInterface
import com.example.response.APIResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject



class MovieRepoImpl @Inject constructor(
    private val api: ApiServiceInterface
) : MovieRepo {

    override suspend fun getMoviesList(): Flow<APIResult<MovieResponse>> {
        return flow {
            emit(APIResult.Loading(true))
            try {
                val response = api.getMoviesList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(APIResult.Success(data = it))
                    } ?: run {
                        emit(APIResult.Error(null, "No data available"))
                    }
                } else {
                    // Map error to a structured format using ErrorHandler
                    val error = ErrorHandler.handleError(HttpException(response))
                    emit(APIResult.Error(null, error.errorMessage))
                }
            } catch (e: Exception) {
                // Use ErrorHandler to generate structured error format
                val error = ErrorHandler.handleError(e)
                emit(APIResult.Error(null, error.errorMessage))
            }
            emit(APIResult.Loading(false))
        }
    }
}
