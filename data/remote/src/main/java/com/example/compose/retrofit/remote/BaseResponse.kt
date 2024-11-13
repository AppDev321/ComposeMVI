package com.example.compose.retrofit.remote

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status")
    val status: Boolean = false

    @SerializedName("version")
    val version: String = ""

    @SerializedName("message")
    var message: String = ""
}