package com.imdb.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.imdb.R
import com.imdb.state.UserState

object HomeDestination : DestinationNav {
    override val route: String = "home_route"
    var userState = UserState()
}

object SearchDestination : DestinationNav {
    override val route: String = "search_route"
}

object ProfileDestination : DestinationNav {
    override val route: String = "profile_route"
}

object PlayDestination : DestinationNav {
    override val route: String = "play_route"
}

object DetailDestination : DestinationNav {
    override val route: String = "detail_route"
}

sealed class Screen(val route: String, @StringRes val name: Int, @DrawableRes val icon: Int) {
    object Home : Screen(HomeDestination.route, R.string.home_screen, R.drawable.ic_google)
    object Search : Screen(SearchDestination.route, R.string.search_screen, R.drawable.ic_facebook)
    object Profile : Screen(ProfileDestination.route, R.string.profile_screen, R.drawable.ic_apple)
    object Play : Screen(PlayDestination.route, R.string.play_screen, R.drawable.ic_google)
}
