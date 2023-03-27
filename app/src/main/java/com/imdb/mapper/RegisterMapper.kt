package com.imdb.mapper

import com.imdb.domain.model.RegisterModel
import com.imdb.state.RegisterState
import com.imdb.state.UserState

fun RegisterState.toRegisterModel() = RegisterModel(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token,
    isLogged = true
)

fun RegisterModel.toRegisterState() = RegisterState(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token,
    isLogged = true
)

fun RegisterState.toUserState() = UserState(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token,
    isLogged = true
)
