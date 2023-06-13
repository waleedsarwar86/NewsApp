package com.criticaltechworks.news.feature.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.criticaltechworks.news.app.di.NewsSource
import com.criticaltechworks.news.core.common.logger.Logger
import com.criticaltechworks.news.core.domain.interactor.GetNewsArticles
import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.ui.UiEvent
import com.criticaltechworks.news.core.ui.util.UiMessage
import com.criticaltechworks.news.core.ui.util.UiMessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    @NewsSource val newsSource: String,
    private val getNewsArticles: GetNewsArticles,
    private val logger: Logger,
) : ViewModel() {

    private val event: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    private val uiMessageManager = UiMessageManager()
    private val articles = MutableStateFlow(listOf<Article>())

    val uiState: StateFlow<ArticlesUiState> = combine(
        uiMessageManager.message,
        getNewsArticles.inProgress,
        articles,
    ) { message, isLoading, articles ->
        ArticlesUiState(
            message = message,
            isLoading = isLoading,
            articles = articles,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ArticlesUiState.Empty,
    )

    init {
        viewModelScope.launch {
            getNewsArticles(newsSource).fold(
                onSuccess = {
                    logger.i(it.toString())
                    articles.value = it
                },
                onFailure = {
                    logger.i(it)
                    uiMessageManager.emitMessage(UiMessage(it))
                },
            )
        }
    }
}
