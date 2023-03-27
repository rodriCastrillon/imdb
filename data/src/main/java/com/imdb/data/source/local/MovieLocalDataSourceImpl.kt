package com.imdb.data.source.local

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.db.MovieEntity
import com.imdb.data.db.MovieQuery
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSourceImpl @Inject constructor(private val query: MovieQuery) :
    MovieLocalDataSource {
    override suspend fun getTopRated(): Either<ErrorFactory, List<MovieEntity>> =
        try {
            val response = withContext(Dispatchers.IO) { query.select() }

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

    override suspend fun insert(entity: List<MovieEntity>) = query.transaction(entity)
}
