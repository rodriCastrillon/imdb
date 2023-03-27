package com.imdb.navigation

import android.app.Activity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imdb.ui.screen.LoginScreen
import com.imdb.ui.screen.SplashScreen

object SplashDestination : DestinationNav {
    override val route: String = "splash_route"
    override val destination: String = "splash_destination"
}

fun NavGraphBuilder.splashGraph(activity: Activity, navController: NavController, popUpTo:(String, String) -> Unit) {

    composable(route = SplashDestination.route) {
        SplashScreen(
            onNavigateLogin = {
                navController.navigate(LoginDestination.route) {
                    popUpTo(SplashDestination.route) { inclusive = true }
                }
            },
            onNavigateDashBoard = {
                popUpTo(SplashDestination.route, DashBoardDestination.route)
            },
            viewModel = hiltViewModel()
        )
    }

    composable(route = LoginDestination.route) {
        LoginScreen(
            activity = activity,
            onNavigateHome = {
                navController.navigate(DashBoardDestination.route) {
                    popUpTo(LoginDestination.route) { inclusive = true }
                }
            },
            onNavigateRegister = {
                navController.navigate(RegisterDestination.route)
            },
            viewModel = hiltViewModel()
        )
    }
}
