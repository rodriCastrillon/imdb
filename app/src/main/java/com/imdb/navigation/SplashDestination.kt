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

fun NavGraphBuilder.splashGraph(activity: Activity, navController: NavController) {

    composable(route = SplashDestination.route) {
        SplashScreen(onNavigate = {
            navController.popBackStack()
            navController.navigate(LoginDestination.route) }
        )
    }

    composable(route = LoginDestination.route) {
        LoginScreen(
            activity = activity,
            onNavigateHome = {
                navController.popBackStack()
                navController.navigate(DashBoardDestination.route)
            },
            onNavigateRegister = {
                navController.navigate(RegisterDestination.route)
            },
            viewModel = hiltViewModel()
        )
    }
}
