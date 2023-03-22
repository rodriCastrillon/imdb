package com.imdb.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.imdb.ui.theme.normal
import com.imdb.ui.theme.whiteF5F5F5

@Composable
fun UsernameTextField() {

    var username by rememberSaveable { mutableStateOf("") }

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
        trailingIcon = {},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}
