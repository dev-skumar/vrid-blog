package dev.skumar.vridblog.core.presentation.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun parseDate(dateString: String): String {
    val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    val localDateTime = LocalDateTime.parse(dateString, inputFormatter)
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy")
    val formattedDate = localDateTime.format(outputFormatter)
    return formattedDate
}
