package com.criticaltechworks.news.core.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NewsAppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        content = content,
    )
}
