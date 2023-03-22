package com.imdb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SplashDestination.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        loginGraph(navController = navController,
            onBack = {
            navController.popBackStack(LoginDestination.route, false)
        })
        splashGraph(navController = navController)
    }
}
