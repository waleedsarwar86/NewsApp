package com.criticaltechworks.news.feature.articles

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.criticaltechworks.news.core.ui.UiEvent

const val HEADLINES_ROUTE = "headlines"
fun NavController.navigateToHeadlines() {
    this.navigate(HEADLINES_ROUTE)
}

fun NavGraphBuilder.headlinesScreen(handleEvent: (UiEvent) -> Unit) {
    composable(HEADLINES_ROUTE) {
        ArticlesRoute(handleEvent = handleEvent)
    }
}
