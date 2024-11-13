package com.example.core.database.repo.dictionary

import com.example.core.database.entity.DictionaryEntity

interface DictionaryRepo {
    suspend fun getDictionaryDataList(): List<DictionaryEntity>
    suspend fun getDictionaryRandomOptions(): List<String>

    suspend fun getDictionaryRandomWord(): List<String>

    suspend fun getDictionaryRandomDictionaryObject(): DictionaryEntity
}