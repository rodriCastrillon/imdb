package com.imdb.domain.repository

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.MovieModel

interface MovieRepository {
    suspend fun getTopRated(): Either<ErrorFactory, List<MovieModel>>
}
