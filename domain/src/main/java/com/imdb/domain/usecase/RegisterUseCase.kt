package com.imdb.domain.usecase

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.RegisterModel

interface RegisterUseCase {
    suspend fun register(model: RegisterModel): Either<ErrorFactory, Boolean>
}
