package com.example.compose.di

import com.example.domain.repository.MovieRepo
import com.example.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMovieUseCase(movieRepo: MovieRepo): MovieUseCase {
        return MovieUseCase(movieRepo)
    }
}