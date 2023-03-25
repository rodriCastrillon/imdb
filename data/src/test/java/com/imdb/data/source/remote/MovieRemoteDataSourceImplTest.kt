package com.imdb.data.source.remote

import com.imdb.common.helper.Either
import com.imdb.data.network.MovieService
import com.imdb.data.source.mapper.toEiterRight
import com.imdb.data.source.network.enqueueResponse
import com.imdb.data.source.response.MovieResponseDummy.movieResponse
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class of [MovieRemoteDataSourceImpl]
 */
@ExperimentalCoroutinesApi
class MovieRemoteDataSourceImplTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieService::class.java)

    private val dataSource = MovieRemoteDataSourceImpl(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Given Movies When topRated is called Then the service return MovieResponse with code 200`() {
        mockWebServer.enqueueResponse("movie_mock.json", 200)
        runBlocking {
            //Given
            val result = movieResponse().toEiterRight()

            //When
            val response = dataSource.getTopRated()

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }
}