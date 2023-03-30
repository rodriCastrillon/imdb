package com.imdb.viewmodel

import app.cash.turbine.test
import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.core.helper.LoadState
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.ui.dummy.getUser
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
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
    private lateinit var registerUseCase: RegisterUseCase

    @Before
    fun setUp() {
        loginUseCase = mockk()
        registerUseCase = mockk()
    }

    @Test
    fun whenUserSignWithUserNameAndEmailSuccess() = runTest {
        // Given
        val registerModel = getUser()
        val username = "test@gmail.com"
        val password = "12345"
        //When
        coEvery { loginUseCase.login(username, password) } returns Either.Right(registerModel)
        viewModel = LoginViewModel(loginUseCase = loginUseCase, registerUseCase = registerUseCase)

        every { viewModel.signManual(email = username, password = password) } just Runs

        //Verify
        viewModel.loginState.test {
            assert(awaitItem() is LoadState.InFlight)
            assert(awaitItem() is LoadState.Success)
            cancelAndConsumeRemainingEvents()
        }
        coVerify(exactly = 1) { loginUseCase.isLogged() }
    }

    @Test
    fun whenSignIsFailed() = runTest {
        //Given
        val username = "test@gmail.com"
        val password = "12345"
        val errorFactory = ErrorFactory(errorCode = 104)
        //When
        coEvery { loginUseCase.login(username, password) } returns Either.Left(errorFactory)
        viewModel = LoginViewModel(loginUseCase = loginUseCase, registerUseCase = registerUseCase)
        every { viewModel.signManual(email = username, password = password) } just Runs

        /*every { viewModel.signManual(email = username, password = password) } just Runs
        every { viewModel.signManual(email = username, password = password) } just Runs*/

        //Verify
        viewModel.loginState.test {

            assert(awaitItem() is LoadState.InFlight)
            assert(awaitItem() is LoadState.Failure)
        }

        Assert.assertEquals(errorFactory.message, "incorrect user or password")

        coVerify(exactly = 1) { loginUseCase.login(username, password) }
        every { viewModel.signManual(email = username, password = password) }
        every {  viewModel.stateErrorMessage }
        every {  viewModel.loginState }
    }
}
