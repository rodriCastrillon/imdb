package com.imdb.data.source.remote

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.response.LatestResponse
import com.imdb.data.response.PopularResponse
import com.imdb.data.response.TopRatedResponse

interface MovieRemoteDataSource {
    suspend fun getTopRated(): Either<ErrorFactory, TopRatedResponse>
    suspend fun getPopular(): Either<ErrorFactory, PopularResponse>
    suspend fun getLatest(): Either<ErrorFactory, LatestResponse>
}
