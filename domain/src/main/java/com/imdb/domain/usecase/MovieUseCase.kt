package com.imdb.domain.usecase

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.domain.model.MovieModel

interface MovieUseCase {
    suspend fun getTopRated(): Either<ErrorFactory, List<MovieModel>>
}
