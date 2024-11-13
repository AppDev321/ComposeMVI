package com.example.core.connection.di

import android.content.Context
import com.example.core.connection.interfaces.ConnectionManager
import com.example.core.connection.manager.ConnectionManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ConnectionModule {

    @Provides
    fun provideConnectionManager(
        @ApplicationContext context: Context
    ): ConnectionManager {
        return ConnectionManagerImpl(context)
    }

}