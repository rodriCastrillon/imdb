package com.imdb.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imdb.R
import com.imdb.ui.components.PasswordTextField
import com.imdb.ui.components.UsernameTextField
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.gray4B4747
import com.imdb.ui.theme.medium
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.small
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.ui.theme.xlarge
import com.imdb.ui.theme.yellowF6C700

@Composable
fun LoginScreen(onNavigateHome: () -> Unit, onNavigateRegister: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(yellowF6C700)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = medium)
        ) {

            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h2.copy(
                    color = black000000,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = stringResource(id = R.string.username), modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal)
            )

            UsernameTextField()

            Text(
                text = stringResource(id = R.string.password), modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal)
            )
            PasswordTextField()

            Text(
                text = stringResource(R.string.forgot_password), modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal),
                style = MaterialTheme.typography.body1.copy(
                    color = gray4B4747
                )
            )

            Button(
                onClick = {
                    onNavigateHome()
                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(backgroundColor = gray4B4747),
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp),
                shape = RoundedCornerShape(normal)
            ) {
                Text(
                    text = stringResource(R.string.button_login),
                    style = MaterialTheme.typography.body1.copy(
                        color = whiteF5F5F5,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.login_with),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal),
                style = MaterialTheme.typography.body1.copy(
                    color = gray4B4747,
                    textAlign = TextAlign.Center
                )
            )

            ButtonsLogin()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = small),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.no_account),
                    style = MaterialTheme.typography.body1.copy(
                        color = gray4B4747
                    )
                )
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.register)),
                    modifier = Modifier.padding(horizontal = normal),
                    style = MaterialTheme.typography.body1.copy(
                        color = black000000,
                        fontWeight = FontWeight.Bold
                    ),
                    onClick = {
                        onNavigateRegister()
                    })
            }

            Text(
                text = stringResource(id = R.string.continue_as_guest), modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal),
                style = MaterialTheme.typography.body1.copy(
                    color = gray4B4747,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

            )

        }
    }
}

@Composable
fun ButtonsLogin() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = xlarge),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(whiteF5F5F5),
            modifier = Modifier.size(70.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_apple),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Login with Apple"
            )
        }
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(whiteF5F5F5),
            modifier = Modifier.size(70.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_facebook),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Login with Facebook"
            )
        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(whiteF5F5F5),
            modifier = Modifier.size(70.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Login with Google"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onNavigateHome = {},
        onNavigateRegister = {})
}

