package com.imdb.data.source.local

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.db.RegisterEntity
import com.imdb.data.db.RegisterQuery
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterLocalDataSourceImpl @Inject constructor(private val local: RegisterQuery) :
    RegisterLocalDataSource {
    override suspend fun register(entity: RegisterEntity): Either<ErrorFactory, Boolean> =

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
