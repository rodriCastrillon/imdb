package com.imdb.domain.mapper

import com.imdb.data.db.UserEntity
import com.imdb.domain.model.RegisterModel
import java.util.Date

fun UserEntity.toRegisterModel() = RegisterModel(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token,
    isLogged = isLogged
)

fun RegisterModel.toRegisterEntity() = UserEntity(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token,
    isLogged = isLogged,
    sessionTime = Date()
)
