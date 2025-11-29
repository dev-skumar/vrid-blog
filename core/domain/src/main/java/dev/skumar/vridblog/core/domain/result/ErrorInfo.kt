package dev.skumar.vridblog.core.domain.result

import dev.skumar.vridblog.core.domain.error.ErrorDialog


data class ErrorInfo(
    val title: String,
    val message: String
) {

    fun getErrorDialog(
        extraInfo: String = "",
        enableRetry: Boolean = false,
        onRetry: () -> Unit = { },
        enableOkay: Boolean = true,
        onOkay: () -> Unit
    ): ErrorDialog = ErrorDialog(
        title = this.title,
        message = this.message,
        extraInfo = extraInfo,
        enableRetry = enableRetry,
        onRetry = onRetry,
        enableOkay = enableOkay,
        onOkay = onOkay
    )

}