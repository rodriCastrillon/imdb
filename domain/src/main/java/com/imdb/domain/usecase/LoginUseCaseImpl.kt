package com.imdb.domain.usecase

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.domain.model.RegisterModel
import com.imdb.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val repository: LoginRepository) : LoginUseCase {
    override suspend fun login(
        email: String,
        password: String
    ): Either<ErrorFactory, RegisterModel> = repository.login(email = email, password = password)

    override suspend fun isLogged(): Either<ErrorFactory, Boolean> = repository.isLogged()
}
