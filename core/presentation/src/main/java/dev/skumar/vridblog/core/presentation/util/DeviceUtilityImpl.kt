package dev.skumar.vridblog.core.presentation.util

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import dev.skumar.vridblog.core.domain.utils.DeviceUtility


class DeviceUtilityImpl(
    private val context: Context
) : DeviceUtility {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override suspend fun isInternetAvailable(): Boolean = context.isInternetAvailable()

}