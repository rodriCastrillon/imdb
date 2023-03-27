package com.imdb.domain.repository

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.source.local.LoginLocalDataSource
import com.imdb.domain.mapper.toRegisterModel
import com.imdb.domain.model.RegisterModel
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val localDataSource: LoginLocalDataSource) :
    LoginRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Either<ErrorFactory, RegisterModel> =
        when (val response = localDataSource.login(email = email, password = password)) {
            is Either.Right -> {
                Either.Right(response.r.toRegisterModel())
            }
            is Either.Left -> {
                Either.Left(response.l)
            }

        }

    override suspend fun isLogged(): Either<ErrorFactory, Boolean> =
        when (val response = localDataSource.isLogged()) {
            is Either.Right -> {
                Either.Right(response.r)
            }
            is Either.Left -> {
                Either.Left(response.l)
            }
        }
}
