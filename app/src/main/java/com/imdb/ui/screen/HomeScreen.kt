package com.imdb.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.imdb.R
import com.imdb.state.MovieState
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.gray9D9C9C
import com.imdb.ui.theme.medium
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.small
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.ui.theme.whiteFFFFFF
import com.imdb.ui.theme.yellowF6C700
import com.imdb.viewmodel.MovieViewModel

@Composable
fun HomeScreen(viewModel: MovieViewModel = hiltViewModel()) {
    val latestState by viewModel.latestState.collectAsState()
    val popularState by viewModel.popularStateState.collectAsState()

    Column {

        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Box(contentAlignment = Alignment.Center) {

                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)

                )

                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play_outline),
                        contentDescription = null,
                        tint = whiteFFFFFF,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            Box {
                Column(
                    modifier = Modifier
                        .padding(normal)
                        .offset(x = 140.dp, y = (20).dp / 2)
                ) {
                    Text(
                        text = "The Super Mario Bros",
                        style = MaterialTheme.typography.body1.copy(
                            color = black000000,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Text(text = "Trailer official")
                }

                Box(
                    Modifier
                        .offset(x = 15.dp, y = (-150).dp / 2)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500/9n2tJBplPbgR2ca05hS5CKXwP2c.jpg"),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .width(100.dp)
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
                .background(whiteF5F5F5)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = normal, horizontal = medium)
        ) {
            Card(
                shape = RoundedCornerShape(small),
                modifier = Modifier.size(width = 10.dp, height = 30.dp)
            ) {
                Divider(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(yellowF6C700)
                )
            }

            Text(
                text = "Las mejore seleccionada", modifier = Modifier.padding(horizontal = normal),
                style = MaterialTheme.typography.h6.copy(
                    color = black000000,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(popularState.data) { _, movie ->
                PopularItem(movie)
            }
        }
    }
}

@Composable
fun PopularItem(state: MovieState) {

    Column(modifier = Modifier.padding(vertical = normal)) {
        Card(
            elevation = 6.dp,
            modifier = Modifier
                .padding(start = normal)
                .clickable { }
        ) {
            Column(
                modifier = Modifier
                    .size(width = 130.dp, height = 250.dp)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(state.backdrop_path),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 130.dp, height = 170.dp)
                )

                Row(modifier = Modifier.padding(horizontal = normal)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rate),
                        contentDescription = null,
                        tint = yellowF6C700
                    )

                    Text(text = state.vote_average.toString())
                }

                Text(
                    text = state.title,
                    modifier = Modifier.padding(horizontal = normal),
                    maxLines = 1
                )

                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = gray9D9C9C,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
