package com.criticaltechworks.news.app.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.window.layout.DisplayFeature
import com.criticaltechworks.news.core.ui.UiEvent
import com.criticaltechworks.news.feature.articles.articlesScreen

@Composable
fun NewsAppNavHost(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
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
        articlesScreen(
            windowSizeClass = windowSizeClass,
            displayFeatures = displayFeatures,
            handleEvent = handleEvent,
        )
    }
}
