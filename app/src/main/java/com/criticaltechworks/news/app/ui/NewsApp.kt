package com.criticaltechworks.news.app.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
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
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets.safeDrawing,
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { _ ->
        NewsAppNavHost(
            navController = appState.navController,
            startDestination = startDestination,
            handleEvent = { handleEvent(uiEvent = it, appState) },
        )
    }
}

fun handleEvent(uiEvent: UiEvent, appState: NewsAppState) {
    val navController = appState.navController
    val startDestination = appState.navStartDestination
    when (uiEvent) {
        is UiEvent.GoBack -> {
            navController.popBackStack()
        }
    }
}
