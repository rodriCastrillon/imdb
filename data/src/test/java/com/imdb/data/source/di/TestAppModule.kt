package com.imdb.data.source.di

import android.content.Context
import androidx.room.Room
import com.imdb.data.db.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
object TestAppModule {

    @Provides
    @Named("imdb_test")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, MovieDataBase::class.java
        ).allowMainThreadQueries()
            .build()
}
