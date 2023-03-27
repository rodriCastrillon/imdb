package com.imdb.domain.repository

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.db.UserEntity
import com.imdb.domain.model.RegisterModel

interface LoginRepository {
    suspend fun login(email:String, password:String): Either<ErrorFactory, RegisterModel>

    suspend fun isLogged(): Either<ErrorFactory, Boolean>
}
