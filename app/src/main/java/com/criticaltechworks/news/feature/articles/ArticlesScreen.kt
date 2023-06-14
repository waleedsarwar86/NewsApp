package com.criticaltechworks.news.feature.articles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest.Builder
import com.criticaltechworks.news.R.string
import com.criticaltechworks.news.core.design.components.NewsAppTopBar
import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.ui.UiEvent
import com.criticaltechworks.news.core.ui.ext.toDisplayDataTime
import com.criticaltechworks.news.feature.articles.ArticlesUiEvent.SetSelectedArticleIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticlesScreen(
    modifier: Modifier = Modifier,
    showTopAppBar: Boolean = true,
    state: ArticlesUiState,
    handleEvent: (UiEvent) -> Unit,

) {
    val snackbarHostState = remember { SnackbarHostState() }
    state.message?.let { message ->
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(message.message)
            // onMessageShown(message.id)
        }
    }
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    Scaffold(
        topBar = {
            if (showTopAppBar) {
                NewsAppTopBar(
                    titleRes = string.app_name,
                    scrollBehavior = scrollBehavior,
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = modifier,
    ) { innerPadding ->
        val contentModifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)

        if (state.isLoading) {
            FullScreenLoading()
        } else {
            ArticleList(
                modifier = contentModifier,
                contentPadding = innerPadding,
                articles = state.articles,
                handleEvent = handleEvent,
            )
        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
    ) {
        CircularProgressIndicator()
    }
}

@Composable
internal fun ArticleList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    articles: List<Article>,
    handleEvent: (UiEvent) -> Unit,
    state: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding,
        content = {
            items(articles.size) { index ->
                ArticleItem(index = index, article = articles[index], handleEvent = handleEvent)
                if (index < articles.size - 1) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
                    )
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleItem(index: Int, article: Article, handleEvent: (UiEvent) -> Unit) {
    ListItem(
        leadingContent = {
            AsyncImage(
                model = Builder(LocalContext.current)
                    .data(article.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp, 56.dp)
                    .clip(shape = MaterialTheme.shapes.small),
            )
        },
        headlineText = { Text(text = article.title, style = MaterialTheme.typography.bodyMedium) },
        supportingText = {
            Text(
                text = article.publishedAt.toDisplayDataTime(),
                style = MaterialTheme.typography.bodySmall,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                handleEvent(SetSelectedArticleIndex(index))
            },
    )
}
