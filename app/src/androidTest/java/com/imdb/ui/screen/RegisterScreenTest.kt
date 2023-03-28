package com.imdb.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.imdb.core.helper.Either
import com.imdb.domain.repository.RegisterRepository
import com.imdb.domain.usecase.RegisterUseCase
import com.imdb.domain.usecase.RegisterUseCaseImpl
import com.imdb.ui.dummy.getUser
import com.imdb.viewmodel.RegisterViewModel
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
class RegisterScreenTest {
    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeRule = createComposeRule()

    private lateinit var viewModel: RegisterViewModel

    @Mock
    private lateinit var useCase: RegisterUseCase

    @Mock
    private lateinit var repository: RegisterRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        hiltTestRule.inject()
        repository = mockk()

        runBlocking {
            coEvery { repository.register(getUser()) } returns Either.Right(true)
        }

        useCase = RegisterUseCaseImpl(repository)
        useCase = RegisterUseCaseImpl(repository)
        viewModel = RegisterViewModel(useCase = useCase)

        composeRule.setContent {
            RegisterScreen(
                onBack = {},
                onNavigateDashBoard = {},
                viewModel = viewModel
            )
        }
    }

    @Test
    fun field_is_displayed() {
        with(composeRule) {
            onNodeWithText("Crear cuenta").assertIsDisplayed()
            onNodeWithText("Nombre").assertIsDisplayed()
            onNodeWithText("Correo electrónico").assertIsDisplayed()
            onNodeWithText("Contraseña").assertIsDisplayed()
            onNodeWithText("Aceptar").assertIsDisplayed()
        }
    }

    @Test
    fun validate_button_accept() {
        composeRule.onNodeWithText("Aceptar").performClick()
    }
}
