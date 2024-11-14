package com.example.remote.di

import com.example.compose.retrofit.executor.NetworkProvider
import com.example.compose.retrofit.remote.GsonProvider
import com.example.remote.service.ApiServiceInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setDateFormat(GsonProvider.ISO_8601_DATE_FORMAT).create()
    }

    @Provides
    @Singleton
    fun provideGsonProvider(): GsonProvider = GsonProvider()



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
