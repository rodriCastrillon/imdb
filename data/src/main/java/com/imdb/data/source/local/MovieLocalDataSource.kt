package com.imdb.data.source.local

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.db.MovieEntity

interface MovieLocalDataSource {
    suspend fun getTopRated(): Either<ErrorFactory, List<MovieEntity>>
    suspend fun getPopular(): Either<ErrorFactory, List<MovieEntity>>
    suspend fun getLatest(): Either<ErrorFactory, MovieEntity>
    suspend fun insert(type:String, entity: List<MovieEntity>)
}
