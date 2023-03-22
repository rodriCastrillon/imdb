package com.imdb.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imdb.R
import com.imdb.ui.components.BackArrow
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.gray4B4747
import com.imdb.ui.theme.gray9D9C9C
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.regular
import com.imdb.ui.theme.small
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.ui.theme.xlarge
import com.imdb.ui.theme.yellowF6C700


@Composable
fun RegisterScreen(onBack: () -> Unit) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.ic_visibility)
    else
        painterResource(id = R.drawable.ic_visibility_off)

    BackHandler {

        onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = yellowF6C700,
                elevation = 0.dp,
                title = { },
                navigationIcon = {
                    BackArrow(onBack = {
                        onBack()
                    })
                },
            )
        }

    ) { paddingValue ->
        Column(
            modifier = Modifier
                .padding(
                    horizontal = xlarge,
                    vertical = paddingValue.calculateTopPadding()
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(normal)
        ) {
            Card(backgroundColor = yellowF6C700) {
                Text(
                    text = stringResource(R.string.app_name),
                    modifier = Modifier.padding(horizontal = regular, vertical = small),
                    style = MaterialTheme.typography.h3.copy(
                        color = black000000,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.create_account),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = regular),
                style = MaterialTheme.typography.h5.copy(
                    color = gray9D9C9C,
                    fontWeight = FontWeight.Normal
                )
            )

            OutlinedTextField(
                value = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal)
                    .background(Color.White, RoundedCornerShape(normal)),
                shape = RoundedCornerShape(normal),
                label = {
                    Text(
                        text = stringResource(id = R.string.name),
                        style = MaterialTheme.typography.body1.copy(
                            color = gray9D9C9C,
                            fontWeight = FontWeight.Normal
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray9D9C9C,
                    unfocusedBorderColor = gray9D9C9C
                ),
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    name = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedTextField(
                value = email,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal)
                    .background(Color.White, RoundedCornerShape(normal)),
                shape = RoundedCornerShape(normal),
                label = {
                    Text(
                        text = stringResource(id = R.string.email),
                        style = MaterialTheme.typography.body1.copy(
                            color = gray9D9C9C,
                            fontWeight = FontWeight.Normal
                        )
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray9D9C9C,
                    unfocusedBorderColor = gray9D9C9C
                ),
                maxLines = 1,
                singleLine = true,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            OutlinedTextField(
                value = password,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = normal)
                    .background(Color.White, RoundedCornerShape(normal)),
                shape = RoundedCornerShape(normal),
                label = {
                    Text(
                        text = stringResource(id = R.string.password),
                        style = MaterialTheme.typography.body1.copy(
                            color = gray9D9C9C,
                            fontWeight = FontWeight.Normal
                        )
                    )
                },
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = gray9D9C9C,
                    unfocusedBorderColor = gray9D9C9C
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
                text = stringResource(id = R.string.required_characters),
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.overline.copy(
                    color = gray9D9C9C,
                    fontWeight = FontWeight.Normal
                )
            )

            Button(
                onClick = {

                },
                enabled = true,
                colors = ButtonDefaults.buttonColors(backgroundColor = gray4B4747),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = xlarge),
                shape = RoundedCornerShape(normal)
            ) {
                Text(
                    text = stringResource(R.string.button_accept),
                    modifier = Modifier.padding(vertical = normal),
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = whiteF5F5F5,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(onBack = {})
}