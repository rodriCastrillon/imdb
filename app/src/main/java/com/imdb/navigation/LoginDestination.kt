package com.imdb.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imdb.ui.screen.MainScreen
import com.imdb.ui.screen.RegisterScreen

object RegisterDestination : DestinationNav {
    override val route = "register_route"
}

object MainDestination : DestinationNav {
    override val route: String = "main_route"
}

fun NavGraphBuilder.loginGraph(
    onBack: () -> Unit,
    popUpTo: (String, String) -> Unit
) {
    composable(route = RegisterDestination.route) {
        RegisterScreen(
            onNavigateDashBoard = {
                HomeDestination.userState = it
                popUpTo(LoginDestination.route, HomeDestination.route)
            },
            onBack = { onBack() },
            viewModel = hiltViewModel()
        )
    }

    composable(route = MainDestination.route) {
        MainScreen()
    }
}
