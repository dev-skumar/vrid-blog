package dev.skumar.vridblog.core.domain.result


sealed interface Result<out D> {

    data object Loading: Result<Nothing>

    data class Success<out D>(val data: D): Result<D>

    data class Error(val info: ErrorInfo): Result<Nothing>

}