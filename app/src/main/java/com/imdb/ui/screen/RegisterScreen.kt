package com.imdb.ui.screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.imdb.R
import com.imdb.core.helper.LoadState
import com.imdb.state.UserState
import com.imdb.ui.components.BackArrow
import com.imdb.ui.components.FieldRequired
import com.imdb.ui.components.LinearProgressBarCustom
import com.imdb.ui.theme.black000000
import com.imdb.ui.theme.gray4B4747
import com.imdb.ui.theme.gray9D9C9C
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.regular
import com.imdb.ui.theme.small
import com.imdb.ui.theme.whiteF5F5F5
import com.imdb.ui.theme.xlarge
import com.imdb.ui.theme.yellowF6C700
import com.imdb.viewmodel.RegisterViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onNavigateDashBoard: (UserState) -> Unit,
    viewModel: RegisterViewModel
) {
    val registerState by viewModel.registerState.collectAsState()
    val resultState = viewModel.resultState.collectAsState().value
    var nameState by rememberSaveable { mutableStateOf("") }
    var emailState by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var passwordState by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                .verticalScroll(rememberScrollState())
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
                value = nameState,
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
                    nameState = it
                    registerState.apply { name = nameState }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            if (!viewModel.isNameFilled) {
                FieldRequired(R.string.name)
            }

            OutlinedTextField(
                value = emailState,
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
                onValueChange = {
                    emailState = it
                    registerState.apply { email = emailState }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            if (!viewModel.isEmailFilled) {
                FieldRequired(R.string.email)
            }

            OutlinedTextField(
                value = passwordState,
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
                    passwordState = it
                    registerState.apply { password = passwordState }
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

            if (!viewModel.isPasswordFilled) {
                FieldRequired(R.string.password)
            }

            if (!viewModel.validatedPassword) {
                Text(
                    text = stringResource(id = R.string.required_characters),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.overline.copy(
                        color = gray9D9C9C,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Button(
                onClick = {
                    keyboardController?.hide()
                    viewModel.register()
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

    val localContext = LocalContext.current
    when (resultState) {
        is LoadState.Failure -> {
            Toast.makeText(localContext, viewModel.stateErrorMessage, Toast.LENGTH_LONG).show()
            viewModel.onClear()
        }
        is LoadState.Loading -> {
            LinearProgressBarCustom()
        }
        is LoadState.Success -> {
            onNavigateDashBoard(viewModel.userSate)
            viewModel.onClear()
        }
        LoadState.InFlight -> {}
    }
}
