package dev.skumar.vridblog.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.skumar.vridblog.core.domain.constants.ErrorMessage
import dev.skumar.vridblog.core.domain.constants.ErrorTitle
import dev.skumar.vridblog.core.domain.error.ErrorDialog
import dev.skumar.vridblog.core.presentation.annotations.PreviewTheme
import dev.skumar.vridblog.core.presentation.components.ModalErrorDialog
import dev.skumar.vridblog.core.presentation.theme.VridBlogTheme


@Composable
fun BaseAppViewPort(
    appNavigation: @Composable () -> Unit,
    errorDialog: ErrorDialog?,
    modifier: Modifier = Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            appNavigation()
        }


        if (errorDialog != null) {

            ModalErrorDialog(
                errorTitle = errorDialog.title,
                errorMsg = errorDialog.message,
                extra = errorDialog.extraInfo,
                enableRetry = errorDialog.enableRetry,
                onRetry = errorDialog.onRetry,
                enableOkay = errorDialog.enableOkay,
                onOkay = errorDialog.onOkay
            )

        }

    }

}




@PreviewTheme
@Composable
fun ErrorDialogPreview() {
    VridBlogTheme {
        BaseAppViewPort(
            appNavigation = { },
            errorDialog = ErrorDialog(
                title = ErrorTitle.NO_INTERNET,
                message = ErrorMessage.NO_INTERNET,
                extraInfo = "Some Extra info",
                enableRetry = true,
                onRetry = { },
                enableOkay = true,
                onOkay = { }
            )
        )
    }
}