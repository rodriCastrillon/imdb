package com.imdb.domain.usecase

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.MovieModel

interface MovieUseCase {
    suspend fun getTopRated(): Either<ErrorFactory, List<MovieModel>>
}
