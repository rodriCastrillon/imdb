package com.imdb.domain.repository

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.domain.mapper.toMovieModel
import com.imdb.domain.model.getMovieEntity
import com.imdb.domain.model.latestResponseMock
import com.imdb.domain.model.popularResponseMock
import com.imdb.domain.model.topRatedResponseMock
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private lateinit var repository: MovieRepositoryImpl
    @MockK
    private lateinit var remoteDataSource: MovieRemoteDataSource
    @MockK
    private lateinit var localDataSource: MovieLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = MovieRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun `Given getTopRated method right value When data source is executed Verify expected response with code 200`() =
        runBlocking {
            //Given
            val listMoviesEntity = getMovieEntity()
            val listMoviesResponse = topRatedResponseMock()

            //When
            coEvery { localDataSource.getTopRated() } returns Either.Right(listMoviesEntity)
            coEvery { remoteDataSource.getTopRated() } returns Either.Right(listMoviesResponse)
            val result = repository.getTopRated()
            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, listMoviesResponse.results.map { it.toMovieModel() })
            Assert.assertTrue(result.r.isNotEmpty())
            coVerify(exactly = 1) {
                remoteDataSource.getTopRated()
            }
        }

    @Test
    fun `Given getTopRated left When repository call fails Then verify error state is 401`() =
        runBlocking {
            //Given
            val errorFactory = ErrorFactory(errorCode = 401)
            //When
            coEvery { remoteDataSource.getTopRated() } returns Either.Left(errorFactory)
            val result = repository.getTopRated()

            //Verify
            Assert.assertTrue(result is Either.Left)
            Assert.assertEquals(401, (result as Either.Left).l.code)

            coVerify {
                remoteDataSource.getTopRated()
            }
        }

    @Test
    fun `Given getPopular method right value When data source is executed Verify expected response with code 200`() =
        runBlocking {
            //Given
            val listMoviesEntity = getMovieEntity()
            val listMoviesResponse = popularResponseMock()

            //When
            coEvery { localDataSource.getPopular() } returns Either.Right(listMoviesEntity)
            coEvery { remoteDataSource.getPopular() } returns Either.Right(listMoviesResponse)
            val result = repository.getPopular()
            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, listMoviesResponse.results.map { it.toMovieModel() })
            Assert.assertTrue(result.r.isNotEmpty())
            coVerify(exactly = 1) {
                remoteDataSource.getPopular()
            }
        }

    @Test
    fun `Given getPopular left When repository call fails Then verify error state is 401`() =
        runBlocking {
            //Given
            val errorFactory = ErrorFactory(errorCode = 401)
            //When
            coEvery { remoteDataSource.getPopular() } returns Either.Left(errorFactory)
            val result = repository.getPopular()

            //Verify
            Assert.assertTrue(result is Either.Left)
            Assert.assertEquals(401, (result as Either.Left).l.code)

            coVerify {
                remoteDataSource.getPopular()
            }
        }

    @Test
    fun `Given getLatest method right value When data source is executed Verify expected response with code 200`() =
        runBlocking {
            //Given
            val listMoviesEntity = getMovieEntity().first()
            val latestResponse = latestResponseMock()

            //When
            coEvery { localDataSource.getLatest() } returns Either.Right(listMoviesEntity)
            coEvery { remoteDataSource.getLatest() } returns Either.Right(latestResponse)
            val result = repository.getLatest()
            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, latestResponse.toMovieModel())
            Assert.assertTrue(result.r.id.isNotEmpty())
            coVerify(exactly = 1) {
                remoteDataSource.getLatest()
            }
        }

    @Test
    fun `Given getLatest left When repository call fails Then verify error state is 401`() =
        runBlocking {
            //Given
            val errorFactory = ErrorFactory(errorCode = 401)
            //When
            coEvery { remoteDataSource.getLatest() } returns Either.Left(errorFactory)
            val result = repository.getLatest()

            //Verify
            Assert.assertTrue(result is Either.Left)
            Assert.assertEquals(401, (result as Either.Left).l.code)

            coVerify {
                remoteDataSource.getLatest()
            }
        }
}
