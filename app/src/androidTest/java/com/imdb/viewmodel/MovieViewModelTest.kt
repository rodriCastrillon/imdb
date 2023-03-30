package com.imdb.viewmodel

import app.cash.turbine.test
import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.usecase.MovieUseCase
import com.imdb.ui.dummy.getTopRated
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test class [MovieViewModel]
 */
@ExperimentalCoroutinesApi
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private lateinit var useCase: MovieUseCase

    @Before
    fun setUp(){
        useCase = mockk()
    }

    @Test
    fun whenTopRatedMethodResponseSuccess() = runTest {
        // Given
        val movieModel = getTopRated()

        //When
        coEvery { useCase.getTopRated() } returns Either.Right(movieModel)
        viewModel = MovieViewModel(useCase = useCase)

        //Verify
        viewModel.movieState.test {
            assert(awaitItem().isLoading)
            assert(awaitItem().data.isNotEmpty())
            cancelAndConsumeRemainingEvents()
        }
        coVerify(exactly = 1) { useCase.getTopRated() }
    }

    @Test
    fun whenTopRatedMethodResponseFailed() = runTest {
        // Given
        val errorFactory = ErrorFactory(errorCode = 102)

        //When
        coEvery { useCase.getTopRated() } returns Either.Left(errorFactory)
        viewModel = MovieViewModel(useCase = useCase)

        //Verify
        viewModel.movieState.test {
            assert(awaitItem().isLoading)
            assert(awaitItem().failed)
            cancelAndConsumeRemainingEvents()
        }
        Assert.assertEquals(errorFactory.message, "no internet connection")
        coVerify(exactly = 1) { useCase.getTopRated() }
    }
}
