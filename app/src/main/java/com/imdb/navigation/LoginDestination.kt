package com.imdb.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.imdb.state.UserState
import com.imdb.ui.screen.DashBoardScreen
import com.imdb.ui.screen.RegisterScreen

object LoginDestination : DestinationNav {
    override val route: String = "login_route"
    override val destination: String = "login_destination"
}

object RegisterDestination : DestinationNav {
    override val route = "register_route"
    override val destination = "register_destination"
}

object DashBoardDestination : DestinationNav {
    override val route = "dashboard_route"
    override val destination = "dashboard_destination"
    var userState = UserState()
}

fun NavGraphBuilder.loginGraph(onBack: () -> Unit, popUpTo: (String, String) -> Unit) {
    composable(route = RegisterDestination.route) {
        RegisterScreen(
            onNavigateDashBoard = {
                DashBoardDestination.userState = it
                popUpTo(LoginDestination.route, DashBoardDestination.route)
            },
            onBack = { onBack() },
            viewModel = hiltViewModel()
        )
    }

    composable(route = DashBoardDestination.route) {
        DashBoardScreen(
            userState = DashBoardDestination.userState,
            viewModel = hiltViewModel()
        )
    }
}
