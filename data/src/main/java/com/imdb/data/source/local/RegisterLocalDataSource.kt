package com.imdb.data.source.local

import com.imdb.common.helper.Either
import com.imdb.common.helper.ErrorFactory
import com.imdb.data.db.RegisterEntity

interface RegisterLocalDataSource {
    suspend fun register(entity: RegisterEntity): Either<ErrorFactory, Boolean>
}
