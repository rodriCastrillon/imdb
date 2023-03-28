package com.imdb.data.source.local

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.db.MovieEntity

interface MovieLocalDataSource {
    suspend fun getTopRated(): Either<ErrorFactory, List<MovieEntity>>
    suspend fun insert(entity: List<MovieEntity>)
}
