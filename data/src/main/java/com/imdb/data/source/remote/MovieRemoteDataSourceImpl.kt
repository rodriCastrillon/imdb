package com.imdb.data.source.remote

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.network.MovieService
import com.imdb.data.response.MovieResponse
import javax.inject.Inject

/**
 * Test class [MovieRemoteDataSourceImplTest]
 */
class MovieRemoteDataSourceImpl @Inject constructor(private val service: MovieService) :
    MovieRemoteDataSource {
    override suspend fun getTopRated(): Either<ErrorFactory, MovieResponse> =
        try {
            val response = service.getTopRated()
            when {
                response.isSuccessful -> {
                    Either.Right(checkNotNull(response.body()))
                }
                else -> {
                    Either.Left(ErrorFactory(response.code()))
                }
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(errorCode = exception.hashCode()))
        }
}
