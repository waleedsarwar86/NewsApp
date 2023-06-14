package com.criticaltechworks.news.feature.articles

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.window.layout.DisplayFeature
import com.criticaltechworks.news.core.ui.UiEvent

const val ARTICLES_ROUTE = "articles"

fun NavGraphBuilder.articlesScreen(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    handleEvent: (UiEvent) -> Unit,
) {
    composable(ARTICLES_ROUTE) {
        ArticlesRoute(
            windowSizeClass = windowSizeClass,
            displayFeatures = displayFeatures,
            handleEvent = handleEvent,
        )
    }
}
