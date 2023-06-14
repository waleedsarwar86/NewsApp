package com.criticaltechworks.news.feature.articles

import com.criticaltechworks.news.core.ui.UiEvent

sealed class ArticlesUiEvent : UiEvent() {
    class SetSelectedArticleIndex(val index: Int) : ArticlesUiEvent()
    class SetIsArticleDetailsOpen(val isArticleDetailsOpen: Boolean) : ArticlesUiEvent()
}

