package com.imdb.data.source.local

import com.imdb.core.helper.Either
import com.imdb.core.helper.ErrorFactory
import com.imdb.core.remoteConfig.RemoteConfig
import com.imdb.data.db.LoginDAO
import com.imdb.data.db.UserEntity
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginLocalDataSourceImpl @Inject constructor(private val query: LoginDAO) :
    LoginLocalDataSource {
    override suspend fun login(
        email: String,
        password: String
    ): Either<ErrorFactory, UserEntity> =
        try {
            val response =
                withContext(Dispatchers.IO) {
                    query.selectByEmailAndPassword(
                        email = email,
                        password = password
                    )
                }
            when {
                response != null -> {
                    withContext(Dispatchers.IO) {
                        query.updateSession()
                    }
                    Either.Right(response)
                }
                else -> Either.Left(ErrorFactory(errorCode = 104))
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun isLogged(): Either<ErrorFactory, UserEntity> =
        try {
            val response = withContext(Dispatchers.IO) { query.select() }

            when {
                response != null -> {
                    when {
                        response.isLogged -> {
                            val sessionTime =
                                (Calendar.getInstance().timeInMillis - checkNotNull(response.sessionTime.time))
                                    .milliseconds.inWholeMinutes
                            when {
                                sessionTime < RemoteConfig.sessionTime -> Either.Right(response)
                                else -> Either.Left(ErrorFactory(errorCode = 106))
                            }
                        }
                        else -> Either.Left(ErrorFactory(errorCode = 107))
                    }
                }
                else -> Either.Left(ErrorFactory(errorCode = 105))
            }
        } catch (exception: Exception) {
            Either.Left(ErrorFactory(errorCode = exception.hashCode()))
        }
}
