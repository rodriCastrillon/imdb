package com.imdb.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.imdb.ui.components.LinearProgressBarCustom
import com.imdb.ui.components.LoadErrorScreen
import com.imdb.ui.theme.medium
import com.imdb.ui.theme.normal
import com.imdb.viewmodel.MovieViewModel

@Composable
fun DashBoardScreen(onNavigate: () -> Unit, viewModel: MovieViewModel) {
    val movieState by viewModel.movieState.collectAsState()

    when {
        movieState.isLoading -> {
            LinearProgressBarCustom()
        }
        movieState.failed -> {
            LoadErrorScreen(viewModel.stateErrorMessage)
        }
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState()
            ) {
                itemsIndexed(movieState.data) { _, movie ->
                    Text(text = movie.title,
                    modifier = Modifier.padding(vertical = normal, horizontal = medium))
                }
            }
        }
    }
}
