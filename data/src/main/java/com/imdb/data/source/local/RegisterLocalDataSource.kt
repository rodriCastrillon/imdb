package com.imdb.data.source.local

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.db.UserEntity

interface RegisterLocalDataSource {
    suspend fun register(entity: UserEntity): Either<ErrorFactory, Boolean>
    suspend fun validateRegister(entity: UserEntity): Either<ErrorFactory, Boolean>
}
