package com.imdb.domain.usecase

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.RegisterModel

interface LoginUseCase {
    suspend fun login(email:String, password:String): Either<ErrorFactory, RegisterModel>

    suspend fun isLogged(): Either<ErrorFactory, RegisterModel>
}
