package com.imdb.domain.repository

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.domain.model.RegisterModel

interface RegisterRepository {
    suspend fun register(model:RegisterModel): Either<ErrorFactory, Boolean>
}
