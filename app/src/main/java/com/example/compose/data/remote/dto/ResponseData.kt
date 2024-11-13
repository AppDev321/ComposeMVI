package com.example.compose.data.remote.dto

import com.squareup.moshi.Json

data class ResponseData(
    @field:Json(name = "Symbol") val symbol: String?,
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Name") val name: String?,

)