package com.criticaltechworks.news.app.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.criticaltechworks.news.app.navigation.NewsAppNavHost
import com.criticaltechworks.news.core.ui.UiEvent

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun NewsApp(
    startDestination: String,
    appState: NewsAppState,
) {
    NewsAppNavHost(
        navController = appState.navController,
        startDestination = startDestination,
        handleEvent = { handleEvent(uiEvent = it, appState) },
    )
}

fun handleEvent(uiEvent: UiEvent, appState: NewsAppState) {
    val navController = appState.navController
    when (uiEvent) {
        is UiEvent.GoBack -> {
            navController.popBackStack()
        }
    }
}
