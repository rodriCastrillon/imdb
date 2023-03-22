package com.imdb.data.di

import android.content.Context
import androidx.room.Room
import com.imdb.common.Constants.DATABASE_NAME
import com.imdb.data.db.MovieDataBase
import com.imdb.data.db.MovieLocal
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.local.MovieLocalDataSourceImpl
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
    fun provideChannelDao(database: MovieDataBase): MovieLocal {
        return database.movieLocal()
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
    fun providerInterface(local: MovieLocal): MovieLocalDataSource =
        MovieLocalDataSourceImpl(local)
}
