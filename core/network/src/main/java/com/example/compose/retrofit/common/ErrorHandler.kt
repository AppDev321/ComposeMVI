package com.example.compose.retrofit.common

import com.example.compose.retrofit.remote.BaseError
import com.example.compose.retrofit.remote.HTTPNotFoundException
import com.example.compose.retrofit.remote.NetworkException
import com.example.compose.retrofit.remote.ResponseErrors
import com.example.compose.retrofit.remote.ServerNotAvailableException
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLPeerUnverifiedException


object ErrorHandler {

    private const val SOMETHING_WENT_WRONG = "Something went wrong, please try again later"

    fun handleError(exception: Throwable): BaseError {
        return when (exception) {
            is HttpException -> {
                val errorBody = exception.response()?.errorBody()?.string().orEmpty()
                val errorMessage = parseErrorMessage(errorBody)
                BaseError(
                    errorMessage = errorMessage,
                    errorCode = mapHttpExceptionCode(exception.code())
                )
            }

            is ServerNotAvailableException -> {
                BaseError(
                    errorMessage = "Server not available",
                    errorCode = ResponseErrors.HTTP_UNAVAILABLE
                )
            }

            is HTTPNotFoundException -> {
                BaseError(
                    errorMessage = "Server not found",
                    errorCode = ResponseErrors.HTTP_NOT_FOUND
                )
            }

            is UnknownHostException,
            is NetworkException,
            is ConnectException -> {
                BaseError(
                    errorMessage = "Internet not available",
                    errorCode = ResponseErrors.CONNECTIVITY_EXCEPTION
                )
            }

            is SSLPeerUnverifiedException -> {
                BaseError(
                    errorMessage = SOMETHING_WENT_WRONG,
                    errorCode = ResponseErrors.UNKNOWN_EXCEPTION
                )
            }

            is IOException,
            is TimeoutException -> {
                BaseError(
                    errorMessage = exception.localizedMessage ?: SOMETHING_WENT_WRONG,
                    errorCode = ResponseErrors.UNKNOWN_EXCEPTION
                )
            }

            else -> {
                BaseError(
                    errorMessage = SOMETHING_WENT_WRONG,
                    errorCode = ResponseErrors.UNKNOWN_EXCEPTION
                )
            }
        }
    }

    private fun parseErrorMessage(error: String): String {
        return try {
            val baseError = Gson().fromJson(error, BaseError::class.java)
            baseError?.message?.takeIf { it.isNotBlank() } ?: baseError?.errorMessage
            ?: SOMETHING_WENT_WRONG
        } catch (e: Exception) {
            SOMETHING_WENT_WRONG
        }
    }

    private fun mapHttpExceptionCode(code: Int): ResponseErrors {
        return when (code) {
            429 -> ResponseErrors.HTTP_TOO_MANY_REQUEST
            500 -> ResponseErrors.INTERNAL_SERVER_ERROR
            401 -> ResponseErrors.HTTP_UNAUTHORIZED
            else -> ResponseErrors.RESPONSE_ERROR
        }
    }
}



