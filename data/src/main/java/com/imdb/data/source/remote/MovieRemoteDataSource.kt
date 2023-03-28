package com.imdb.data.source.remote

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.response.MovieResponse

interface MovieRemoteDataSource {
    suspend fun getTopRated(): Either<ErrorFactory, MovieResponse>
}
