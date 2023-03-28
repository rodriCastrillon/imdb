package com.imdb.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.imdb.core.helper.Either
import com.imdb.domain.repository.LoginRepository
import com.imdb.domain.repository.RegisterRepository
import com.imdb.domain.usecase.LoginUseCase
import com.imdb.domain.usecase.LoginUseCaseImpl
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.domain.usecase.RegisterUseCaseImpl
import com.imdb.ui.MainActivity
import com.imdb.ui.dummy.getUser
import com.imdb.viewmodel.LoginViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeRule = createComposeRule()

    private lateinit var viewModel: LoginViewModel

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @Mock
    private lateinit var loginRepository: LoginRepository

    @Mock
    private lateinit var registerUseCase: RegisterUseCase

    @Mock
    private lateinit var registerRepository: RegisterRepository

    @Mock
    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        hiltTestRule.inject()
        loginRepository = mockk()
        registerRepository = mockk()
        activity = mockk()

        runBlocking {
            val user = getUser()
            coEvery { loginRepository.login("test@gmail.com", "1234") } returns Either.Right(user)
        }

        loginUseCase = LoginUseCaseImpl(loginRepository)
        registerUseCase = RegisterUseCaseImpl(registerRepository)
        viewModel = LoginViewModel(loginUseCase = loginUseCase, registerUseCase = registerUseCase)

        composeRule.setContent {
            LoginScreen(
                activity = activity,
                onNavigateHome = {},
                onNavigateRegister = {},
                viewModel = viewModel
            )
        }
    }

    @Test
    fun field_is_displayed() {
        with(composeRule) {
            onNodeWithText("Usuario").assertIsDisplayed()
            onNodeWithText("Contraseña").assertIsDisplayed()
            onNodeWithText("¿Olvidaste la contraseña?").assertIsDisplayed()
            onNodeWithText("Login").assertIsDisplayed()
            onNodeWithText("O puedes ingresar con").assertIsDisplayed()
            onNodeWithText("¿No tienes cuenta?").assertIsDisplayed()
            onNodeWithText("Registrate").assertIsDisplayed()
            onNodeWithText("Continuar como invitado").assertIsDisplayed()
        }
    }

    @Test
    fun validate_button_login() {
        val buttonLogin = hasText("Login") and hasClickAction()
        composeRule.onNodeWithText("is required").assertDoesNotExist()
        composeRule.onNode(buttonLogin).performClick()
    }

    @Test
    fun validate_button_register() {
        composeRule.onNodeWithText("Registrate").performClick()
    }
}
