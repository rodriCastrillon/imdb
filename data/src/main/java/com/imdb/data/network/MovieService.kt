package com.imdb.data.network

import com.imdb.core.Constants.TOP_RATED_METHOD
import com.imdb.data.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieService {
    @GET(TOP_RATED_METHOD)
    suspend fun getTopRated(): Response<MovieResponse>
}
