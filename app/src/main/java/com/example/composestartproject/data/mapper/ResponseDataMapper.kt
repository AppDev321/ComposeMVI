package com.example.composestartproject.data.mapper

import com.example.composestartproject.data.remote.dto.ResponseData

fun ResponseData.toCompanyInfo(): ResponseData {
    return ResponseData(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: ""
    )
}