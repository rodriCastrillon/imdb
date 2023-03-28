package com.imdb.domain.repository

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.RegisterModel

interface RegisterRepository {
    suspend fun register(model:RegisterModel): Either<ErrorFactory, Boolean>
}
