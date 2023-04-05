package com.imdb.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imdb.ui.screen.HomeScreen
import com.imdb.ui.screen.PlayScreen
import com.imdb.ui.screen.ProfileScreen
import com.imdb.ui.screen.SearchScreen

@Composable
fun GraphBottomBar(
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeDestination.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Search.route) {
            SearchScreen(
                userState = HomeDestination.userState,
                viewModel = hiltViewModel()
            )
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }

        composable(route = Screen.Play.route) {
            PlayScreen()
        }
    }
}
