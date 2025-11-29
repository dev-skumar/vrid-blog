package dev.skumar.vridblog.core.domain.exceptions


sealed class DatabaseException : RuntimeException() {

    data class EntityNotFound(val msg: String): DatabaseException()

}