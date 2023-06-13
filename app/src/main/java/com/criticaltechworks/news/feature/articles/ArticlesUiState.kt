package com.criticaltechworks.news.feature.articles

import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.ui.util.UiMessage

data class ArticlesUiState(
    val message: UiMessage? = null,
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),

) {
    companion object {
        val Empty = ArticlesUiState()
    }
}
