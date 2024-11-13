package com.example.remote.di

import com.example.compose.retrofit.executor.NetworkProvider
import com.example.remote.`interface`.ApiServiceInterface
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String {
        return ApiServiceInterface.BASE_URL
    }

    @Provides
    fun provideNetworkProvider(gson: Gson, baseURL: String): NetworkProvider {
        return NetworkProvider(gson, baseURL)
    }



    //Because i use single ApiServiceInterface for all thats i use this if you have any interffaces then use same like below
    @Provides
    fun provideApiService(networkProvider: NetworkProvider) =
        networkProvider.create(ApiServiceInterface::class.java)

}
