package com.imdb.domain.di

import com.imdb.data.source.local.LoginLocalDataSource
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.local.RegisterLocalDataSource
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.domain.repository.LoginRepository
import com.imdb.domain.repository.LoginRepositoryImpl
import com.imdb.domain.repository.MovieRepository
import com.imdb.domain.repository.MovieRepositoryImpl
import com.imdb.domain.repository.RegisterRepository
import com.imdb.domain.repository.RegisterRepositoryImpl
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.domain.usecase.LoginUseCaseImpl
import com.imdb.domain.usecase.MovieUseCase
import com.imdb.domain.usecase.MovieUseCaseImpl
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.domain.usecase.RegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun providerMovieRepository(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource
    ): MovieRepository = MovieRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Provides
    fun providerMovieUseCase(repository: MovieRepository): MovieUseCase =
        MovieUseCaseImpl(repository = repository)

    @Provides
    fun providerRegisterRepository(localDataSource: RegisterLocalDataSource): RegisterRepository =
        RegisterRepositoryImpl(
            localDataSource = localDataSource
        )

    @Provides
    fun providerRegisterUseCase(repository: RegisterRepository): RegisterUseCase =
        RegisterUseCaseImpl(repository = repository)

    @Provides
    fun providerLoginRepository(localDataSource: LoginLocalDataSource): LoginRepository =
        LoginRepositoryImpl(
            localDataSource = localDataSource
        )

    @Provides
    fun providerLoginUseCase(repository: LoginRepository): LoginUseCase =
        LoginUseCaseImpl(repository = repository)
}
