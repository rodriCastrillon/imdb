package com.imdb.data.source.local

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.db.UserEntity

interface LoginLocalDataSource {
    suspend fun login(email:String, password:String): Either<ErrorFactory, UserEntity>

    suspend fun isLogged(): Either<ErrorFactory, Boolean>
}
