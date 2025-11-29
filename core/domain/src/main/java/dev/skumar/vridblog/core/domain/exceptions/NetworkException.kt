package dev.skumar.vridblog.core.domain.exceptions


sealed class NetworkException : RuntimeException() {

    data class NoInternetException(val msg: String): NetworkException()

    data class BadRequestException(val msg: String): NetworkException()

    data class NotFoundException(val msg: String): NetworkException()

    data class InternalServerException(val msg: String): NetworkException()

    data class UnknownNetworkException(val statusCode: Int, val msg: String): NetworkException()

}