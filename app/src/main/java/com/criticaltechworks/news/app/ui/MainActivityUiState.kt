package com.criticaltechworks.news.app.ui

import com.criticaltechworks.news.feature.articles.ARTICLES_ROUTE

data class MainActivityUiState(
    val isAuthenticated: Boolean = false,
    val startDestination: String = ARTICLES_ROUTE,
)
