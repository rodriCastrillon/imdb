package com.imdb.domain.usecase

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.domain.model.RegisterModel
import com.imdb.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val repository: RegisterRepository) :
    RegisterUseCase {
    override suspend fun register(model: RegisterModel): Either<ErrorFactory, Boolean> = repository.register(model = model)
}
