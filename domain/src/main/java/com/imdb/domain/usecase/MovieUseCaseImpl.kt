package com.imdb.domain.usecase

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.MovieModel
import com.imdb.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(private val repository: MovieRepository) : MovieUseCase {
    override suspend fun getTopRated(): Either<ErrorFactory, List<MovieModel>> = repository.getTopRated()
    override suspend fun getPopular(): Either<ErrorFactory, List<MovieModel>> = repository.getPopular()

    override suspend fun getLatest(): Either<ErrorFactory, MovieModel> = repository.getLatest()
}
