package com.imdb.state

data class UserState(
    var id: String = "",
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastname: String = "",
    var provider: String = "",
    val urlPhoto: String = "",
    val token: String = "",
    val isLogged: Boolean = false
)
