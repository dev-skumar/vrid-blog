package dev.skumar.vridblog.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.skumar.vridblog.core.presentation.theme.bodyNormal
import dev.skumar.vridblog.core.presentation.theme.l1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalErrorDialog(
    errorTitle: String,
    errorMsg: String,
    extra: String,
    enableRetry: Boolean,
    onRetry: () -> Unit,
    enableOkay: Boolean,
    onOkay: () -> Unit,
    modifier: Modifier = Modifier
) {


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        BasicAlertDialog(
            onDismissRequest = {},
            modifier = Modifier
                .fillMaxWidth(0.85f),
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.error)
                        .padding(16.dp)
                ) {
                    Text(
                        text = errorTitle,
                        style = MaterialTheme.typography.l1,
                        color = MaterialTheme.colorScheme.onError,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                ) {

                    Text(
                        text = errorMsg,
                        style = MaterialTheme.typography.bodyNormal,
                        color = MaterialTheme.colorScheme.onErrorContainer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )

                    if(extra != "") {
                        Text(
                            text = extra,
                            style = MaterialTheme.typography.bodyNormal,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp, top = 16.dp)
                        )
                    }

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.errorContainer)
                        .padding(8.dp)
                ) {

                    if (enableRetry) {
                        OutlinedButton(
                            onClick = onRetry,
                            colors = ButtonDefaults.outlinedButtonColors().copy(
                                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onErrorContainer)
                        ) {
                            Text(
                                text = "Retry"
                            )
                        }
                    }

                    if (enableOkay) {
                        OutlinedButton(
                            onClick = onOkay,
                            colors = ButtonDefaults.outlinedButtonColors().copy(
                                contentColor = MaterialTheme.colorScheme.onErrorContainer,
                                containerColor = MaterialTheme.colorScheme.errorContainer,
                            ),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onErrorContainer)
                        ) {
                            Text(
                                text = "Okay"
                            )
                        }
                    }

                }

            }

        }

    }


}