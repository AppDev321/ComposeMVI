package com.example.compose.di

import com.example.remote.repository.MovieRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepo(movieRepoImpl: MovieRepoImpl): com.example.domain.repository.MovieRepo
}
