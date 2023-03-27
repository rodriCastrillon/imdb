package com.imdb.domain.model

data class RegisterModel(
    val id: String,
    val email: String,
    val password: String,
    val name: String,
    val lastname: String,
    val provider: String,
    val urlPhoto: String,
    val token: String,
    val isLogged: Boolean
)
