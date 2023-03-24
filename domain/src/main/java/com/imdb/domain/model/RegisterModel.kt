package com.imdb.domain.model

data class RegisterModel(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val lastname: String,
    val provider: String
)
