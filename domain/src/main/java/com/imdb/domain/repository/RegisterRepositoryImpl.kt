package com.imdb.domain.repository

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.source.local.RegisterLocalDataSource
import com.imdb.domain.mapper.toRegisterEntity
import com.imdb.domain.model.RegisterModel
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val localDataSource: RegisterLocalDataSource) :
    RegisterRepository {
    override suspend fun register(model:RegisterModel): Either<ErrorFactory, Boolean> =
        localDataSource.register(model.toRegisterEntity())
}
