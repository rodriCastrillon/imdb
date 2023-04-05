package com.imdb.data.network

import com.imdb.core.Constants.LATEST_METHOD
import com.imdb.core.Constants.POPULAR_METHOD
import com.imdb.core.Constants.TOP_RATED_METHOD
import com.imdb.data.response.LatestResponse
import com.imdb.data.response.PopularResponse
import com.imdb.data.response.TopRatedResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET(TOP_RATED_METHOD)
    suspend fun getTopRated(): Response<TopRatedResponse>

    @GET(POPULAR_METHOD)
    suspend fun getPopular(): Response<PopularResponse>

    @GET(LATEST_METHOD)
    suspend fun getLatest(): Response<LatestResponse>
}
