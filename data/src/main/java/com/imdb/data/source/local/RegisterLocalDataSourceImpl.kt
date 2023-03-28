package com.imdb.data.source.local

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.data.db.RegisterDAO
import com.imdb.data.db.UserEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterLocalDataSourceImpl @Inject constructor(private val local: RegisterDAO) :
    RegisterLocalDataSource {
    override suspend fun register(entity: UserEntity): Either<ErrorFactory, Boolean> =

        try {
            withContext(Dispatchers.IO) { local.transaction(entity) }
            Either.Right(true)
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(exception.hashCode()))
        }

    override suspend fun validateRegister(entity: UserEntity): Either<ErrorFactory, Boolean> =

        try {

            when (withContext(Dispatchers.IO) { local.selectByEmail(entity.email) }) {
                0 -> {
                    local.transaction(entity)
                    Either.Right(true)
                }
                else -> {
                    Either.Left(ErrorFactory(errorCode = 103))
                }
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(exception.hashCode()))
        }
}
