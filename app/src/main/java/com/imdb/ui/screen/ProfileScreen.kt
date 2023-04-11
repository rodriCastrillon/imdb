package com.imdb.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.imdb.R
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.gray4B4747
import com.imdb.ui.theme.gray9D9C9C
import com.imdb.ui.theme.large
import com.imdb.ui.theme.medium
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.regular
import com.imdb.ui.theme.small
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.ui.theme.whiteFFFFFF
import com.imdb.ui.theme.xlarge
import com.imdb.ui.theme.xxlarge
import com.imdb.ui.theme.yellowF6C700

@Composable
fun ProfileScreen() {
    val url =
        "https://media.licdn.com/dms/image/D4E03AQF6w9MBohaMEA/profile-displayphoto-shrink_800_800/0/1677087234917?e=2147483647&v=beta&t=kn2jD8vT-FR9c993kcphnP7iseax3EPwJGSko34Zmis"

    val list = listOf("Calificaciones", "Listas", "Comentarios", "otros")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = xxlarge)
            .background(whiteFFFFFF)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(medium)
                .background(whiteFFFFFF),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        BorderStroke(1.dp, gray4B4747),
                        CircleShape
                    )
                    .padding(1.dp)
                    .clip(CircleShape)
            )

            Text(text = "Rodrigo Castrillon",
                style = MaterialTheme.typography.body2.copy(
                    color = black000000,
                    fontWeight = FontWeight.Bold
                ))

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = yellowF6C700
                )
            }
        }

        Divider(
            color = gray4B4747.copy(alpha = 0.5F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(medium)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { _, item ->
                CardProfile(item)
            }
        }

        Divider(
            color = gray4B4747.copy(alpha = 0.5F),
            modifier = Modifier
                .fillMaxWidth()
                .size(30.dp)
                .padding(vertical = xlarge)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            state = rememberLazyListState()
        ) {
            itemsIndexed(list) { _, item ->
                Spacer(modifier = Modifier.height(20.dp).fillMaxWidth().background(whiteF5F5F5))
                ListDummy()
            }
        }
    }
}

@Composable
fun CardProfile(value: String) {
    Card(
        backgroundColor = whiteFFFFFF,
        elevation = 6.dp,
        shape = RoundedCornerShape(small),
        modifier = Modifier
            .size(width = 180.dp, height = 160.dp)
            .padding(start = medium)
            .clickable { }
    ) {
        Column(
            modifier = Modifier
                .size(width = 140.dp, height = 120.dp)
                .padding(normal)
        ) {
            Card(shape = RoundedCornerShape(small)) {
                Column(
                    modifier = Modifier
                        .background(whiteF5F5F5)
                        .padding(normal)
                ) {
                    Text(text = "Calificar y obtener recomendaciones")
                }
            }

            Text(text = value)

            Text(text = "0")
        }
    }
}

@Composable
fun ListDummy() {

    Column(modifier = Modifier.padding(horizontal = medium, vertical = normal)) {
        Row() {
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

            Text(text = "Lista de seguimiento", modifier = Modifier.padding(horizontal = normal),
                style = MaterialTheme.typography.body1.copy(
                    color = black000000,
                    fontWeight = FontWeight.Bold
                ))
        }

        Text(text = "Crea una lista de seguimiento para no perder ninguna película o serie de tv.",
            modifier = Modifier.padding(vertical = normal))

        Button(
            onClick = {},
            enabled = true,
            colors = ButtonDefaults.buttonColors(backgroundColor = yellowF6C700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = large, bottom = normal),
            shape = RoundedCornerShape(normal)
        ) {
            Text(
                text = "Empieza tu lista de seguimiento",
                modifier = Modifier.padding(vertical = normal),
                style = MaterialTheme.typography.body1.copy(
                    color = gray4B4747,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}
