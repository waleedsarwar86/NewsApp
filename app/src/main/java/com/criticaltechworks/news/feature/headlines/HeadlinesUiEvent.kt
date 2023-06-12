package com.criticaltechworks.news.feature.headlines

import com.criticaltechworks.news.core.ui.UiEvent

sealed class HeadlinesUiEvent : UiEvent() {
    object OnRefresh : HeadlinesUiEvent()
}
