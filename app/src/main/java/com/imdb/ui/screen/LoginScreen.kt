package com.imdb.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(onNavigate: () -> Unit) {
    Column {
        Text(text = "This is the login screen")

        Button(onClick = {
            onNavigate()
        }) {
            Text(text = "Next")
        }
    }
}
