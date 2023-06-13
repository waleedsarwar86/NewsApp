package com.criticaltechworks.news.feature.articles

import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.ui.UiEvent

sealed class ArticlesUiEvent : UiEvent() {
    class OnArticleClicked(val article: Article) : UiEvent()

    object OnRefresh : ArticlesUiEvent()
}
