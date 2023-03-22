package com.imdb.data.source.local

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.db.MovieEntity
import com.imdb.data.db.MovieLocal
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSourceImpl @Inject constructor(private val local:MovieLocal) :MovieLocalDataSource {
    override suspend fun getTopRated(): Either<ErrorFactory, List<MovieEntity>> =
        try {
            val response = withContext(Dispatchers.IO) { local.select() }

            when {
                response.isNotEmpty() -> {
                    Either.Right(response)
                }
                else -> {
                    Either.Left(ErrorFactory(errorCode = -2))
                }
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory())
        }

    override suspend fun insert(movieEntity: List<MovieEntity>) {
        local.transaction(movieEntity)
    }
}
