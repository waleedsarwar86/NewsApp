package com.criticaltechworks.news.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.criticaltechworks.news.core.ui.UiEvent
import com.criticaltechworks.news.feature.articles.headlinesScreen

@Composable
fun NewsAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String,
    handleEvent: (UiEvent) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        headlinesScreen(handleEvent)
    }
}
