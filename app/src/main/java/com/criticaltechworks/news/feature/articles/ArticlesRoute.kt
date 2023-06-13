package com.criticaltechworks.news.feature.articles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.criticaltechworks.news.core.ui.UiEvent

@Composable
fun ArticlesRoute(
    modifier: Modifier = Modifier,
    viewModel: ArticlesViewModel = hiltViewModel(),
    handleEvent: (UiEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticlesScreen(
        uiState = uiState,
        modifier = modifier,
        handleEvent = { uiEvent ->
            when (uiEvent) {
                is ArticlesUiEvent -> {
                }

                else -> {
                    handleEvent(uiEvent)
                }
            }
        },
    )
}

