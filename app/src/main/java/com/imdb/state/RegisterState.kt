package com.imdb.state

data class RegisterState(
    var id: String = "",
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastname: String = "",
    var provider: String = "",
    val urlPhoto: String = "",
    val token: String = ""
)
