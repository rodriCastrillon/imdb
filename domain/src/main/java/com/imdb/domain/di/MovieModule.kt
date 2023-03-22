package com.imdb.domain.di

import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.domain.repository.MovieRepository
import com.imdb.domain.repository.MovieRepositoryImpl
import com.imdb.domain.usecase.MovieUseCase
import com.imdb.domain.usecase.MovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {
    @Provides
    fun providerRepository(remoteDataSource: MovieRemoteDataSource,
                           localDataSource: MovieLocalDataSource): MovieRepository = MovieRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Provides
    fun providerUseCase(repository: MovieRepository): MovieUseCase =
        MovieUseCaseImpl(repository = repository)
}
