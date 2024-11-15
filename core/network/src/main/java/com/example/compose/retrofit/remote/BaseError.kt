package com.example.compose.retrofit.remote

import com.google.gson.annotations.SerializedName


data class BaseError(
    @SerializedName("result")
    val errorMessage: String = "",
    val errorCode: ResponseErrors = ResponseErrors.RESPONSE_ERROR,
    val errorBody: String = ""
) : BaseResponse()

enum class ResponseErrors {
    HTTP_UNAUTHORIZED,
    HTTP_TOO_MANY_REQUEST,
    HTTP_BAD_REQUEST,
    HTTP_NOT_FOUND,
    CONNECTIVITY_EXCEPTION,
    HTTP_UNAVAILABLE,
    RESPONSE_ERROR,
    UNKNOWN_EXCEPTION,
    INTERNAL_SERVER_ERROR,
}