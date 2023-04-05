package com.imdb.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.imdb.R
import com.imdb.core.helper.LoadState
import com.imdb.state.UserState
import com.imdb.ui.components.LinearProgressBarCustom
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.yellowF6C700
import com.imdb.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    onNavigateLogin: () -> Unit,
    onNavigateHome: (UserState) -> Unit,
    viewModel: SplashViewModel
) {
    val localContext = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is LoadState.Loading -> LinearProgressBarCustom()
        is LoadState.Failure -> {
            if(viewModel.stateErrorMessage.isNotEmpty()){
                Toast.makeText(localContext, viewModel.stateErrorMessage, Toast.LENGTH_LONG).show()
            }
            onNavigateLogin()
            viewModel.onClear()
        }
        is LoadState.Success -> {
            onNavigateHome(viewModel.userSate)
            viewModel.onClear()
        }
        is LoadState.InFlight -> {}
    }

    Splash()
}

@Composable
fun Splash() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(yellowF6C700),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h2.copy(
                color = black000000,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Splash()
}