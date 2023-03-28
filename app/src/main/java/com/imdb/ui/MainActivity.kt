package com.imdb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.imdb.navigation.AppNavHost
import com.imdb.ui.theme.ImdbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImdbTheme {
                AppNavHost(activity = this, navController = rememberNavController())
            }
        }
    }
}
