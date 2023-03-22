package com.imdb.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.imdb.ui.components.BackArrow
import com.imdb.ui.theme.yellowF6C700


@Composable
fun RegisterScreen(onBack: () -> Unit) {
    BackHandler {

        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = yellowF6C700,
                title = { },
                navigationIcon = {
                    BackArrow(onBack = {
                        onBack()
                    })
                },
            )
        }

    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Text(text = "This is register screen")
        }
    }
}
