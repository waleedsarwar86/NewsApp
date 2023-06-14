package com.criticaltechworks.news.feature.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(
    modifier: Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
    showTopAppBar: Boolean,
    state: Article?,
) {
    if (state == null) {
        return
    }
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
    Scaffold(
        topBar = {
            if (showTopAppBar) {
                NewsAppTopBar(
                    titleRes = string.app_name,
                    scrollBehavior = scrollBehavior,
                )
            }
        },
        modifier = Modifier.fillMaxWidth(),
    ) { innerPadding ->
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            if (state.image != null) {
                HeaderImage(state.image)
                Spacer(Modifier.height(16.dp))
            }
            Text(text = state.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(Modifier.height(8.dp))
            if (state.description != null) {

                Text(text = state.description, style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(8.dp))
            }
            if (state.content != null) {

                Text(text = state.content, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun HeaderImage(imageUrl: String?) {
    val imageModifier = Modifier
        .heightIn(min = 180.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.medium)

    AsyncImage(
        model = Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = imageModifier,
    )
}


