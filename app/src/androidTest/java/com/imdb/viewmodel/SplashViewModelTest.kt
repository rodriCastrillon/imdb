package com.imdb.viewmodel

import app.cash.turbine.test
import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.core.helper.LoadState
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.ui.dummy.getUser
import com.imdb.viewmodel.SplashViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test class [SplashViewModel]
 */
@ExperimentalCoroutinesApi
class SplashViewModelTest {
    private lateinit var viewModel: SplashViewModel
    private lateinit var useCase: LoginUseCase

    @Before
    fun setUp() {
        useCase = mockk()
    }

    @Test
    fun whenIsLoggedIsSuccess() = runTest {
        // Given
        val registerModel = getUser()

        //When
        coEvery { useCase.isLogged() } returns Either.Right(registerModel)
        viewModel = SplashViewModel(useCase = useCase)

        //Verify
        viewModel.loginState.test {
            assert(awaitItem() is LoadState.InFlight)
            assert(awaitItem() is LoadState.Success)
            cancelAndConsumeRemainingEvents()
        }
        coVerify(exactly = 1) { useCase.isLogged() }
    }

    @Test
    fun whenIsLoggedIsFailed() = runTest {
        //Given
        val errorFactory = ErrorFactory(errorCode = 105)
        //When
        coEvery { useCase.isLogged() } returns Either.Left(errorFactory)
        viewModel = SplashViewModel(useCase = useCase)

        //Verify
        viewModel.loginState.test {
            assert(awaitItem() is LoadState.InFlight)
            assert(awaitItem() is LoadState.Failure)
        }

        Assert.assertEquals(errorFactory.message, "user without session")

        coVerify(exactly = 1) { useCase.isLogged() }
    }
}
