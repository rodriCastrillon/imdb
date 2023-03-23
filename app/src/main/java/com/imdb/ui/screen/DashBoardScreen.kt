package com.imdb.ui.screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.imdb.R
import com.imdb.common.toYYYY
import com.imdb.state.MovieState
import com.imdb.ui.components.LinearProgressBarCustom
import com.imdb.ui.components.LoadErrorScreen
import com.imdb.ui.components.SearchView
import com.imdb.ui.theme.gray4B4747
import com.imdb.ui.theme.gray9D9C9C
import com.imdb.ui.theme.large
import com.imdb.ui.theme.medium
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.viewmodel.MovieViewModel

@Composable
fun DashBoardScreen(onNavigate: () -> Unit, viewModel: MovieViewModel) {
    val movieState by viewModel.movieState.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val searchedText = textState.value.text

    when {
        movieState.isLoading -> {
            LinearProgressBarCustom()
        }
        movieState.failed -> {
            LoadErrorScreen(viewModel.stateErrorMessage)
        }
        else -> {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                SearchView(
                    state = textState,
                    placeholder = stringResource(id = R.string.search),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = medium,vertical = large)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    state = rememberLazyListState()
                ) {
                    itemsIndexed(movieState.data) { _, movie ->
                        ItemMovie(movieState = movie)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemMovie(movieState: MovieState) {

    Divider(
        color = gray9D9C9C.copy(alpha = 0.2F),
        modifier = Modifier

            .padding(horizontal = medium)
    )
    Card(
        backgroundColor = whiteF5F5F5,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxSize()
            .clickable { }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = normal)
        ) {
            Image(
                painter = rememberAsyncImagePainter(movieState.poster_path),
                contentDescription = "Movie image",
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                contentScale = ContentScale.None
            )

            Column() {

                Text(
                    text = movieState.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = normal, horizontal = medium),
                    style = MaterialTheme.typography.body1.copy(
                        color = gray4B4747,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = movieState.release_date.toYYYY(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = normal, horizontal = medium),
                    style = MaterialTheme.typography.body1.copy(
                        color = gray9D9C9C,
                        fontWeight = FontWeight.Normal
                    )
                )

                Text(
                    text = movieState.overview,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = normal, horizontal = medium),
                    style = MaterialTheme.typography.body1.copy(
                        color = gray9D9C9C,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}