package com.example.response

sealed class APIResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : APIResult<T>(data)
    class Error<T>(data: T?, message: String?) : APIResult<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : APIResult<T>(null)
}