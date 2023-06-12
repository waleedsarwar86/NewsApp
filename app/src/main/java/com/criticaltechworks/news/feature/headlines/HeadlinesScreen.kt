package com.criticaltechworks.news.feature.headlines

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.criticaltechworks.news.core.ui.UiEvent

@Composable
fun HeadlinesRoute(
    modifier: Modifier = Modifier,
    viewModel: HeadlinesViewModel = hiltViewModel(),
    handleEvent: (UiEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FoldersScreen(
        uiState = uiState,
        modifier = modifier,
        handleEvent = { uiEvent ->
            when (uiEvent) {
                is HeadlinesUiEvent -> {
                }

                else -> {
                    handleEvent(uiEvent)
                }
            }
        },
    )
}

@Composable
internal fun FoldersScreen(
    uiState: HeadlinesUiState,
    modifier: Modifier = Modifier,
    handleEvent: (UiEvent) -> Unit,
) {
}
