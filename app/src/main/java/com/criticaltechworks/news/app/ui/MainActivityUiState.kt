package com.criticaltechworks.news.app.ui

import com.criticaltechworks.news.feature.articles.HEADLINES_ROUTE

data class MainActivityUiState(
    val isLoading: Boolean = true,
    val startDestination: String = HEADLINES_ROUTE,
)
