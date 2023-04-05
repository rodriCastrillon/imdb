package com.imdb.data.source.local

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.db.MovieDAO
import com.imdb.data.db.MovieEntity
import com.imdb.data.mapper.MovieType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSourceImpl @Inject constructor(private val query: MovieDAO) :
    MovieLocalDataSource {
    override suspend fun getTopRated(): Either<ErrorFactory, List<MovieEntity>> =
        try {
            val response = withContext(Dispatchers.IO) { query.select(MovieType.TopRated.name) }

            when {
                response.isNotEmpty() -> {
                    Either.Right(response)
                }
                else -> {
                    Either.Left(ErrorFactory(errorCode = 101))
                }
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun getPopular(): Either<ErrorFactory, List<MovieEntity>> =
        try {
            val response = withContext(Dispatchers.IO) { query.select(MovieType.Popular.name) }

            when {
                response.isNotEmpty() -> {
                    Either.Right(response)
                }
                else -> {
                    Either.Left(ErrorFactory(errorCode = 101))
                }
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun getLatest(): Either<ErrorFactory, MovieEntity> =
        try {
            val response = withContext(Dispatchers.IO) { query.select(MovieType.Latest.name) }

            when {
                response.isNotEmpty() -> {
                    Either.Right(response.single())
                }
                else -> {
                    Either.Left(ErrorFactory(errorCode = 101))
                }
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun insert(type:String, entity: List<MovieEntity>) = query.transaction(type, entity)
}
