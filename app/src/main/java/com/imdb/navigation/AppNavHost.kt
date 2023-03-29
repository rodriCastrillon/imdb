package com.imdb.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    activity: Activity,
    startDestination: String = SplashDestination.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        splashGraph(
            activity = activity,
            navController = navController,
            popUpTo = { origin, destination ->
                navController.navigate(destination) { popUpTo(origin) { inclusive = true } }
            })

        loginGraph(onBack = {
            navController.popBackStack(LoginDestination.route, false)
        }
        ) { origin, destination ->
            navController.navigate(destination) { popUpTo(origin) { inclusive = true } }
        }

    }
}
