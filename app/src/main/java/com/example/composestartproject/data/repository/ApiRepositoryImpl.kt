package com.example.composestartproject.data.repository

import com.example.composestartproject.data.csv.CSVParser
import com.example.composestartproject.data.remote.RemoteApi
import com.example.composestartproject.data.remote.dto.ResponseData
import com.example.composestartproject.domain.repositary.ApiRepository
import com.example.composestartproject.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepositoryImpl @Inject constructor(
    private val remoteApi: RemoteApi,
    private val responseCSVParser: CSVParser<ResponseData>,
    ) : ApiRepository {


    override suspend fun getListings(): Flow<Resources<String>> {
        return flow {
            emit(Resources.Loading(true))
            try {
                val response = remoteApi.getListings()
               val paredData =  responseCSVParser.parse(response.byteStream())
                emit(
                    Resources.Success(
                        data = paredData.toString()
                    )
                )

            } catch (e: Exception) {
                emit(Resources.Error(null, "Couldn't load data"))
            }

            emit(Resources.Loading(false))

        }
    }
}