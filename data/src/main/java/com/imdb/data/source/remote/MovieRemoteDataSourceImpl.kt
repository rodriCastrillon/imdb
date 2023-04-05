package com.imdb.data.source.remote

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.network.MovieService
import com.imdb.data.response.LatestResponse
import com.imdb.data.response.PopularResponse
import com.imdb.data.response.TopRatedResponse
import javax.inject.Inject

/**
 * Test class [MovieRemoteDataSourceImplTest]
 */
class MovieRemoteDataSourceImpl @Inject constructor(private val service: MovieService) :
    MovieRemoteDataSource {
    override suspend fun getTopRated(): Either<ErrorFactory, TopRatedResponse> =
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

    override suspend fun getPopular(): Either<ErrorFactory, PopularResponse> =
        try {
            val response = service.getPopular()
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

    override suspend fun getLatest(): Either<ErrorFactory, LatestResponse> =
        try {
            val response = service.getLatest()
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
