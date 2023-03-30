package com.imdb.data.di

import android.content.Context
import androidx.room.Room
import com.imdb.core.Constants.DATABASE_NAME
import com.imdb.data.db.LoginDAO
import com.imdb.data.db.MovieDAO
import com.imdb.data.db.MovieDataBase
import com.imdb.data.db.RegisterDAO
import com.imdb.data.source.local.LoginLocalDataSource
import com.imdb.data.source.local.LoginLocalDataSourceImpl
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
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: MovieDataBase): MovieDAO {
        return database.movieLocal()
    }

    @Provides
    @Singleton
    fun provideRegisterDao(database: MovieDataBase): RegisterDAO {
        return database.registerLocal()
    }

    @Provides
    @Singleton
    fun provideLoginDao(database: MovieDataBase): LoginDAO {
        return database.loginLocal()
    }


    @Singleton
    @Provides
    fun providerMovieDataSource(query: MovieDAO): MovieLocalDataSource =
        MovieLocalDataSourceImpl(query)

    @Singleton
    @Provides
    fun providerRegisterDataSource(query: RegisterDAO): RegisterLocalDataSource =
        RegisterLocalDataSourceImpl(query)

    @Singleton
    @Provides
    fun providerLoginDataSource(query: LoginDAO): LoginLocalDataSource =
        LoginLocalDataSourceImpl(query)
}
