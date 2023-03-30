package com.imdb.domain.repository

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.domain.mapper.toMovieModel
import com.imdb.domain.model.getMovieEntity
import com.imdb.domain.model.getMovieResponse
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
    fun `Given getTopRated right value When data source is executed Verify expected response`() =
        runBlocking {
            //Given
            val listMoviesEntity = getMovieEntity()
            val listMoviesResponse = getMovieResponse()

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
}
