package com.imdb.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imdb.ui.screen.LoginScreen
import com.imdb.ui.screen.SplashScreen

object SplashDestination : DestinationNav {
    override val route: String = "splash_route"
    override val destination: String = "splash_destination"
}

fun NavGraphBuilder.splashGraph(navController: NavController) {
    composable(route = SplashDestination.route) {
        SplashScreen(onNavigate = {
            navController.popBackStack()
            navController.navigate(LoginDestination.route)
        })
    }

    composable(route = LoginDestination.route) {
        LoginScreen(onNavigate = {})
    }
}
