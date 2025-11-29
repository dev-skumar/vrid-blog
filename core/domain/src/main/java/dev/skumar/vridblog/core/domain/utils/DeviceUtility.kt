package dev.skumar.vridblog.core.domain.utils

interface DeviceUtility {

    suspend fun isInternetAvailable(): Boolean

}