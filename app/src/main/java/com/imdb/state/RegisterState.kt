package com.imdb.state

data class RegisterState(
    var id: Int = 0,
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastname: String = "",
    var provider: String = ""
)
