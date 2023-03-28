package com.imdb.domain.repository

import com.imdb.core.helper.Either
import com.imdb.data.source.local.MovieLocalDataSource
import com.imdb.data.source.remote.MovieRemoteDataSource
import com.imdb.domain.model.getTopRated
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
            val topRated = getTopRated()
            //When
            coEvery { repository.getTopRated() } returns Either.Right(topRated)
            val result = repository.getTopRated()
                //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, topRated)
            Assert.assertTrue(result.r.isNotEmpty())
        }
}
