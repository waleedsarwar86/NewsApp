package com.criticaltechworks.news.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.criticaltechworks.news.app.ui.MainActivityUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState: StateFlow<MainActivityUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(100)
            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }
}
