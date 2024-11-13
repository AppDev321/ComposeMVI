package com.example.compose.data.mapper

import com.example.compose.data.remote.dto.ResponseData

fun ResponseData.toCompanyInfo(): ResponseData {
    return ResponseData(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: ""
    )
}