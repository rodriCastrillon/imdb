package com.imdb.common.helper

import com.imdb.common.Constants.INTERNET_CONNECTION

open class ErrorFactory(private val errorCode: Int = 100) : CustomError() {

    private val httpStatusCode =
        HttpStatusCode.values().firstOrNull { statusCode -> statusCode.code == errorCode }
            ?: HttpStatusCode.Unknown

    override fun toString(): String {
        return if (errorCode == HttpStatusCode.InternetConnection.code) {
            INTERNET_CONNECTION
        } else {
            "ErrorLoadData" +
                    "\ncode: $errorCode (${httpStatusCode.name})" +
                    "\nmessage: ${super.message}"
        }
    }
}

open class CustomError : Error() {

    override fun toString(): String {
        return "CustomError(message: $message)"
    }
}

enum class HttpStatusCode(val code: Int) {

    // Custom Error
    Unknown(101),
    InternetConnection(102),
    UserExist(103),

    // Client Errors
    BadRequest(400),
    Unauthorized(401),
    PaymentRequired(402),
    Forbidden(403),
    NotFound(404),

    // Server Errors
    InternalServerError(500),
    NotImplemented(501),
    BadGateway(502),
    ServiceUnavailable(503),
    GatewayTimeout(504),
    HttpVersionNotSupported(505),
    VariantAlsoNegates(506),
    InsufficientStorage(507),
    LoopDetected(508),
    NotExtended(510),
    NetworkAuthenticationRequired(511);
}