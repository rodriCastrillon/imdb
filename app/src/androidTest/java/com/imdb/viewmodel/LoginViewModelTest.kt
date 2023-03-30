package com.imdb.viewmodel

import app.cash.turbine.test
import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.core.helper.LoadState
import com.imdb.domain.repository.LoginRepository
import com.imdb.domain.repository.RegisterRepository
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.domain.usecase.LoginUseCaseImpl
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.domain.usecase.RegisterUseCaseImpl
import com.imdb.ui.dummy.getUser
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

/**
 * Test class [LoginViewModel]
 */
@ExperimentalCoroutinesApi
class LoginViewModelTest {
    private lateinit var viewModel: LoginViewModel

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var registerUseCase: RegisterUseCase

    @Mock
    private lateinit var registerRepository: RegisterRepository

    @Before
    fun setUp() {
        loginRepository = mockk()
        registerRepository = mockk()
    }

    @Test
    fun whenUserSignWithUserNameAndEmailSuccess() = runTest {
        // Given
        val username = "test@gmail.com"
        val password = "12345"

        runBlocking {
            val user = getUser()
            coEvery { loginRepository.login("test@gmail.com", "1234") } returns Either.Right(user)
        }

        //When
        loginUseCase = LoginUseCaseImpl(loginRepository)
        registerUseCase = RegisterUseCaseImpl(registerRepository)
        viewModel = LoginViewModel(loginUseCase = loginUseCase, registerUseCase = registerUseCase)

        //every { viewModel.signManual(email = username, password = password) } just Runs

        //Verify
        viewModel.loginState.test {
            assert(awaitItem() is LoadState.InFlight)
            viewModel.signManual(email = username, password = password)
            assert(awaitItem() is LoadState.Success)
            //cancelAndConsumeRemainingEvents()
        }
        coVerify(exactly = 1) {
            loginUseCase.isLogged()
        }
        coVerify(exactly = 1) {
            viewModel.signManual(email = username, password = password)
        }
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
