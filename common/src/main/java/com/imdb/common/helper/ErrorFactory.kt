package com.imdb.common.helper

open class ErrorFactory(errorCode: Int) : ErrorData {
    private val error =
        HttpStatusCode.values().firstOrNull { statusCode -> statusCode.code == errorCode }
            ?: HttpStatusCode.Unknown

    override val message: String = error.name
    override val code:Int = errorCode
}

interface ErrorData{
    val code:Int
    val message:String
}
enum class HttpStatusCode(val code: Int) {
    // Custom Error
    Unknown(101),
    NoInternetConnection(102),
    TheUserAlreadyExists(103),
    IncorrectUserOrPassword(104),
    UserWithoutSession(105),
    SessionHasExpired(106),
    NoActiveSession(107),

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