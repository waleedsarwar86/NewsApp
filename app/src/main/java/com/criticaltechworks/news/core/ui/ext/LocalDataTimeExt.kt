package com.criticaltechworks.news.core.ui.ext

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.toDisplayDataTime(): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy, h:mm a")
    return this.format(formatter)
}
