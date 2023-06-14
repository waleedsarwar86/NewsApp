package com.criticaltechworks.news.app.ui

import com.criticaltechworks.news.feature.articles.ARTICLES_ROUTE

data class MainActivityUiState(
    val isLoading: Boolean = true,
    val startDestination: String = ARTICLES_ROUTE,
)
