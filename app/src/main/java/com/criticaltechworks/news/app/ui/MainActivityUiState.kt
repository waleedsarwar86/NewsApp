package com.criticaltechworks.news.app.ui

import com.criticaltechworks.news.feature.headlines.HEADLINES_ROUTE

data class MainActivityUiState(
    val isLoading: Boolean = true,
    val startDestination: String = HEADLINES_ROUTE,
)
