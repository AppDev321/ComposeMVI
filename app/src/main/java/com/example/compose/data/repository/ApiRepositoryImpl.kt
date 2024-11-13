package com.example.compose.data.repository

import com.example.compose.data.csv.CSVParser
import com.example.compose.data.remote.RemoteApi
import com.example.compose.data.remote.dto.ResponseData
import com.example.compose.domain.repositary.ApiRepository
import com.example.compose.utils.Resources
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