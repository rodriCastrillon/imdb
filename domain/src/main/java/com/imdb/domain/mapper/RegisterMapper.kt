package com.imdb.domain.mapper

import com.imdb.data.db.RegisterEntity
import com.imdb.domain.model.RegisterModel

fun RegisterEntity.toRegisterModel() = RegisterModel(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token
)

fun RegisterModel.toRegisterEntity() = RegisterEntity(
    id = id,
    email = email,
    password = password,
    name = name,
    lastname = lastname,
    provider = provider,
    urlPhoto = urlPhoto,
    token = token
)
