package com.imdb.data.di

import android.content.Context
import androidx.room.Room
import com.imdb.common.Constants.DATABASE_NAME
import com.imdb.data.db.MovieDataBase
import com.imdb.data.db.MovieQuery
import com.imdb.data.db.RegisterQuery
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.local.MovieLocalDataSourceImpl
import com.imdb.data.source.local.RegisterLocalDataSource
import com.imdb.data.source.local.RegisterLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDataBase): MovieQuery {
        return database.movieLocal()
    }

    @Provides
    @Singleton
    fun provideRegisterDao(database: MovieDataBase): RegisterQuery {
        return database.registerLocal()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providerMovieDataSource(query: MovieQuery): MovieLocalDataSource =
        MovieLocalDataSourceImpl(query)

    @Singleton
    @Provides
    fun providerRegisterDataSource(query: RegisterQuery): RegisterLocalDataSource =
        RegisterLocalDataSourceImpl(query)
}
