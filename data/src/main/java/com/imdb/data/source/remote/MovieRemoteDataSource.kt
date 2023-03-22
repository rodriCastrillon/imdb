package com.imdb.data.source.remote

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.response.MovieResponse

interface MovieRemoteDataSource {
    suspend fun getTopRated(): Either<ErrorFactory, MovieResponse>
}
