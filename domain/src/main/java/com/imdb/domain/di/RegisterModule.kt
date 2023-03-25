package com.imdb.domain.di

import com.imdb.data.source.local.RegisterLocalDataSource
import com.imdb.domain.repository.RegisterRepository
import com.imdb.domain.repository.RegisterRepositoryImpl
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.domain.usecase.RegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {
    @Provides
    fun providerRepository(localDataSource: RegisterLocalDataSource): RegisterRepository = RegisterRepositoryImpl(
        localDataSource = localDataSource
    )

    @Provides
    fun providerUseCase(repository: RegisterRepository): RegisterUseCase =
        RegisterUseCaseImpl(repository = repository)
}
