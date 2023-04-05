package com.imdb.data.source.remote

import com.imdb.core.helper.Either
import com.imdb.data.network.MovieService
import com.imdb.data.source.mapper.toEiterRight
import com.imdb.data.source.network.enqueueResponse
import com.imdb.data.source.response.MovieResponseDummy.latestResponseMock
import com.imdb.data.source.response.MovieResponseDummy.popularResponseMock
import com.imdb.data.source.response.MovieResponseDummy.topRatedResponseMock
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
    fun `Given Movies When topRated method is called Then the service return TopRatedResponse with code 200`() {
        mockWebServer.enqueueResponse("topRated_mock.json", 200)
        runBlocking {
            //Given
            val result = topRatedResponseMock().toEiterRight()

            //When
            val response = dataSource.getTopRated()

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }

    @Test
    fun `Given Movies When popular method is called Then the service return PopularResponse with code 200`() {
        mockWebServer.enqueueResponse("popular_mock.json", 200)
        runBlocking {
            //Given
            val result = popularResponseMock().toEiterRight()

            //When
            val response = dataSource.getPopular()

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }

    @Test
    fun `Given Movies When getLatest method is called Then the service return LatestResponse with code 200`() {
        mockWebServer.enqueueResponse("latest_mock.json", 200)
        runBlocking {
            //Given
            val result = latestResponseMock().toEiterRight()

            //When
            val response = dataSource.getLatest()

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }
}