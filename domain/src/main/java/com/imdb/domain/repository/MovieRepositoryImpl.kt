package com.imdb.domain.repository

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.core.helper.NetworkMonitor
import com.imdb.data.mapper.MovieType
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
                    localDataSource.insert(MovieType.TopRated.name, response.r.results.map { it.toMovieEntity() })
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
                    Either.Left(ErrorFactory(102))
                }
            }
        }
    }

    override suspend fun getPopular(): Either<ErrorFactory, List<MovieModel>> {
        if (NetworkMonitor().isConnected()) {
            return when (val response = remoteDataSource.getPopular()) {
                is Either.Right -> {
                    localDataSource.insert(MovieType.Popular.name, response.r.results.map { it.toMovieEntity() })
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
                    Either.Left(ErrorFactory(102))
                }
            }
        }
    }

    override suspend fun getLatest(): Either<ErrorFactory, MovieModel> {
        if (NetworkMonitor().isConnected()) {
            return when (val response = remoteDataSource.getLatest()) {
                is Either.Right -> {
                    localDataSource.insert(MovieType.Latest.name, listOf(response.r.toMovieEntity()))
                    Either.Right(response.r.toMovieModel())
                }
                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        } else {
            return when (val response = localDataSource.getLatest()) {
                is Either.Right -> {
                    Either.Right(response.r.toMovieModel())
                }

                is Either.Left -> {
                    Either.Left(ErrorFactory(102))
                }
            }
        }
    }
}
