package dev.skumar.vridblog.core.presentation.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "theme - DARK",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "theme -  LIGHT",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class PreviewTheme