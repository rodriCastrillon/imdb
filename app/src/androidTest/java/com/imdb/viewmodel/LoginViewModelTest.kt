package com.imdb.viewmodel

import app.cash.turbine.test
import com.imdb.core.extensionFunctions.toHashSha1
import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.core.helper.LoadState
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.ui.dummy.getUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test class [LoginViewModel]
 */
@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setUp() {
        loginUseCase = mockk()
        viewModel = LoginViewModel(loginUseCase = loginUseCase, registerUseCase = mockk())
    }

    @Test
    fun whenUserSignWithUserNameAndEmailSuccess() = runTest {
        // Given
        val username = "test@gmail.com"
        val password = "12345"
        val passwordHash = (username.plus(password).toHashSha1())
        val user = getUser()

        //When
        coEvery { loginUseCase.login(username, passwordHash) } returns Either.Right(user)
        viewModel.signManual(email = username, password = password)

        //Verify
        viewModel.loginState.test {
            assert(awaitItem() is LoadState.InFlight)
            assert(awaitItem() is LoadState.Success)
            cancelAndConsumeRemainingEvents()
        }
        coVerify(exactly = 1) { loginUseCase.login(username, passwordHash)}
    }

    @Test
    fun whenSignIsFailed() = runTest {
        // Given
        val username = "test@gmail.com"
        val password = "12345"
        val passwordHash = (username.plus(password).toHashSha1())
        val errorFactory = ErrorFactory(errorCode = 104)

        //When
        coEvery { loginUseCase.login(username, passwordHash) } returns Either.Left(errorFactory)
        viewModel.signManual(email = username, password = password)

        //Verify
        viewModel.loginState.test {
            assert(awaitItem() is LoadState.InFlight)
            assert(awaitItem() is LoadState.Failure)
            cancelAndConsumeRemainingEvents()
        }

        Assert.assertEquals(errorFactory.message, "incorrect user or password")

        coVerify(exactly = 1) { loginUseCase.login(username, passwordHash)}
    }
}
