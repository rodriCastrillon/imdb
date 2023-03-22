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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imdb.R
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.gray4B4747
import com.imdb.ui.theme.gray9D9C9C
import com.imdb.ui.theme.large
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.small
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.ui.theme.xlarge
import com.imdb.ui.theme.yellowF6C700

@Composable
fun LoginScreen(onNavigateHome: () -> Unit, onNavigateRegister: () -> Unit) {
    val focusManager = LocalFocusManager.current
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.ic_visibility)
    else
        painterResource(id = R.drawable.ic_visibility_off)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(yellowF6C700)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = large)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.fillMaxWidth().padding(top = large),
                style = MaterialTheme.typography.h2.copy(
                    color = black000000,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = stringResource(id = R.string.username),
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body1.copy(
                    color = gray9D9C9C,
                    fontWeight = FontWeight.Bold
                )
            )

            OutlinedTextField(
                value = username,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(whiteF5F5F5, RoundedCornerShape(normal)),
                shape = RoundedCornerShape(normal),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = whiteF5F5F5,
                    unfocusedBorderColor = whiteF5F5F5
                ),
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    username = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )

            Text(
                text = stringResource(id = R.string.password),
                modifier = Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.body1.copy(
                    color = gray9D9C9C,
                    fontWeight = FontWeight.Bold
                )
            )

            OutlinedTextField(
                value = password,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(whiteF5F5F5, RoundedCornerShape(normal)),
                shape = RoundedCornerShape(normal),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = whiteF5F5F5,
                    unfocusedBorderColor = whiteF5F5F5
                ),
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    password = it
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(
                            painter = icon,
                            contentDescription = "Visibility Icon"
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation()
            )

            Text(
                text = stringResource(R.string.forgot_password), modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal),
                style = MaterialTheme.typography.body1.copy(
                    color = gray9D9C9C
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
                    .padding(top = large),
                shape = RoundedCornerShape(normal)
            ) {
                Text(
                    text = stringResource(R.string.button_login),
                    modifier = Modifier.padding(vertical = normal),
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
                    .padding(vertical = large),
                style = MaterialTheme.typography.subtitle1.copy(
                    color = gray9D9C9C,
                    textAlign = TextAlign.Center
                )
            )

            ButtonsLogin()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = small, vertical = large),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.no_account),
                    style = MaterialTheme.typography.body1.copy(
                        color = gray4B4747,
                        fontWeight = FontWeight.Normal
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

