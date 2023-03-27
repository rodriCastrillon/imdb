package com.imdb

import androidx.compose.ui.test.junit4.createComposeRule
import com.imdb.ui.screen.DashBoardScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun buttonLogin() {
        rule.setContent { DashBoardScreen() }
    }
}
