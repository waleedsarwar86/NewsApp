package com.criticaltechworks.news.feature.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.criticaltechworks.news.core.ui.UiEvent
import com.criticaltechworks.news.core.ui.util.ObservableLoadingCounter
import com.criticaltechworks.news.core.ui.util.UiMessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor() : ViewModel() {
    private val event: MutableSharedFlow<UiEvent> = MutableSharedFlow()
    private val loadingState = ObservableLoadingCounter()
    private val uiMessageManager = UiMessageManager()

    val uiState: StateFlow<HeadlinesUiState> = combine(
        loadingState.observable,
        uiMessageManager.message,
    ) { _ ->
        HeadlinesUiState.Empty
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HeadlinesUiState.Empty,
    )
}
