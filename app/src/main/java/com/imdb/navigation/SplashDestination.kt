package com.imdb.navigation

import android.app.Activity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imdb.ui.screen.LoginScreen
import com.imdb.ui.screen.MainScreen
import com.imdb.ui.screen.SplashScreen

object SplashDestination : DestinationNav {
    override val route: String = "splash_route"
}

object LoginDestination : DestinationNav {
    override val route: String = "login_route"
}

fun NavGraphBuilder.splashGraph(
    activity: Activity,
    navController: NavController,
    popUpTo: (String, String) -> Unit
) {

    composable(route = SplashDestination.route) {
        SplashScreen(
            onNavigateLogin = {
                navController.navigate(LoginDestination.route) {
                    popUpTo(SplashDestination.route) { inclusive = true }
                }
            },
            onNavigateHome = {
                HomeDestination.userState = it
                popUpTo(SplashDestination.route, MainDestination.route)
            },
            viewModel = hiltViewModel()
        )
    }

    composable(route = LoginDestination.route) {
        LoginScreen(
            activity = activity,
            onNavigateHome = {
                HomeDestination.userState = it
                popUpTo(LoginDestination.route, MainDestination.route)
            },
            onNavigateRegister = {
                navController.navigate(RegisterDestination.route)
            },
            viewModel = hiltViewModel()
        )
    }
}
