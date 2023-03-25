package com.imdb.mapper

import com.imdb.domain.model.RegisterModel
import com.imdb.state.RegisterState

fun RegisterState.toRegisterModel() = RegisterModel(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token
)
