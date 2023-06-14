package com.criticaltechworks.news.feature.articles

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.layout.DisplayFeature
import com.criticaltechworks.news.core.ui.UiEvent
import com.criticaltechworks.news.core.ui.components.ListDetail
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy

@Composable
fun ArticlesRoute(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    viewModel: ArticlesViewModel = hiltViewModel(),
    handleEvent: (UiEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val widthSizeClass by rememberUpdatedState(windowSizeClass.widthSizeClass)
    val isTwoPane = when (widthSizeClass) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> false
        WindowWidthSizeClass.Expanded -> !uiState.isLoading
        else -> true
    }

    ListDetail(
        isDetailOpen = uiState.isArticleDetailOpen,
        setIsDetailOpen = { viewModel.handleEvent(ArticlesUiEvent.SetIsArticleDetailsOpen(it)) },
        showListAndDetail = isTwoPane,
        detailKey = uiState.selectedArticleIndex,
        list = {
            ArticlesScreen(
                modifier = Modifier,
                showTopAppBar = !isTwoPane,
                state = uiState,
                handleEvent = { uiEvent ->
                    when (uiEvent) {
                        is ArticlesUiEvent -> {
                            viewModel.handleEvent(uiEvent)
                        }

                        else -> {
                            handleEvent(uiEvent)
                        }
                    }
                },
            )
        },
        detail = {
            ArticleDetailsScreen(
                modifier = Modifier,
                showTopAppBar = !isTwoPane,
                state = uiState.selectedArticle,
            )
        },
        twoPaneStrategy = HorizontalTwoPaneStrategy(
            splitFraction = 1f / 2f,
        ),
        displayFeatures = displayFeatures,
        modifier = Modifier,
    )
}


