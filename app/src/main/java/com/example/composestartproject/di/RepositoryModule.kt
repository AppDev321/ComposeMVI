package com.example.composestartproject.di

import com.example.composestartproject.data.csv.CSVParser
import com.example.composestartproject.data.csv.ResponseParser
import com.example.composestartproject.data.remote.dto.ResponseData
import com.example.composestartproject.data.repository.ApiRepositoryImpl
import com.example.composestartproject.domain.repositary.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindResponseParser(
        responseParser: ResponseParser
    ): CSVParser<ResponseData>


    @Binds
    @Singleton
    abstract  fun bindApiRepository(
        apiRepositoryImpl: ApiRepositoryImpl
    ) : ApiRepository
}