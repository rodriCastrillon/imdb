package com.imdb

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.imdb.ui.screen.DashBoardScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 1)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)

    var composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        composeTestRule.setContent { DashBoardScreen()
        }
    }

    @Test
    fun buttonLogin() {
        composeTestRule.onNodeWithText("Login").performClick()
    }
}
