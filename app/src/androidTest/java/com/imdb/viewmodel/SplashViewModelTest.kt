package com.imdb.viewmodel

import com.imdb.domain.repository.LoginRepository
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.domain.usecase.LoginUseCaseImpl
import com.imdb.state.UserState
import com.imdb.ui.config.DispatcherProvider
import com.imdb.ui.config.TestDispatcherProvider
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {
    private lateinit var dispatcherProvider: DispatcherProvider

    private lateinit var viewModel: SplashViewModel

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        loginRepository = mockk()
        loginUseCase = LoginUseCaseImpl(loginRepository)
        viewModel = SplashViewModel(useCase = loginUseCase)
    }

    @Test
    fun get() = runBlocking {
        val data = UserState()
        doReturn(flowOf(UserState())).`when`(loginUseCase).login("rodrigo@gmail.com", "12345")
        val viewModel = SplashViewModel(loginUseCase)
        /*viewModel.loginState.test {

        }

        assertEquals(result.fla, data)*/
        coVerify { loginUseCase.login("", "") }
    }

}
