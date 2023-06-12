package com.criticaltechworks.news.feature.headlines

import com.criticaltechworks.news.core.ui.util.UiMessage

data class HeadlinesUiState(
    val isLoading: Boolean = false,
    val message: UiMessage? = null,

) {
    companion object {
        val Empty = HeadlinesUiState()
    }
}
