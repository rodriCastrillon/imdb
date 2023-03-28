package com.imdb.ui.screen


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.imdb.core.helper.Either
import com.imdb.domain.repository.MovieRepository
import com.imdb.domain.usecase.MovieUseCase
import com.imdb.domain.usecase.MovieUseCaseImpl
import com.imdb.state.UserState
import com.imdb.ui.dummy.getTopRated
import com.imdb.viewmodel.MovieViewModel
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
class DashBoardScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    var composeRule = createComposeRule()

    private lateinit var viewModel: MovieViewModel
    @Mock
    private lateinit var useCase: MovieUseCase
    @Mock
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        hiltTestRule.inject()
        repository = mockk()

        runBlocking {
            val topRated = getTopRated()
            coEvery { repository.getTopRated() } returns Either.Right(topRated)
        }

        useCase = MovieUseCaseImpl(repository)
        viewModel = MovieViewModel(useCase)

        composeRule.setContent {
            DashBoardScreen(userState = UserState(), viewModel = viewModel)
        }
    }

    @Test
    fun field_is_displayed_dashboard() {
        with(composeRule) {
            onNodeWithText("Search").assertIsDisplayed()
            onNodeWithText("The Godfather").assertIsDisplayed()
            onNodeWithText("The Shawshank Redemption").assertIsDisplayed()
        }
    }
}
