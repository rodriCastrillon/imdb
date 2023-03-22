package com.imdb.domain.repository

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.domain.model.MovieModel

interface MovieRepository {
    suspend fun getTopRated(): Either<ErrorFactory, List<MovieModel>>
}
