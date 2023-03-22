package com.imdb.domain.repository

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.common.helper.NetworkMonitor
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.domain.mapper.toMovieEntity
import com.imdb.domain.mapper.toMovieModel
import com.imdb.domain.model.MovieModel
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getTopRated(): Either<ErrorFactory, List<MovieModel>> {
        if (NetworkMonitor().isConnected()) {
            return when (val response = remoteDataSource.getTopRated()) {
                is Either.Right -> {
                    localDataSource.insert(response.r.results.map { it.toMovieEntity() })
                    Either.Right(response.r.results.map { it.toMovieModel() })
                }
                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        } else {
            return when (val response = localDataSource.getTopRated()) {
                is Either.Right -> {
                    Either.Right(response.r.map { it.toMovieModel() })
                }

                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        }
    }
}
