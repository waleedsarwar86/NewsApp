package com.criticaltechworks.news.core.domain.interactor

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

abstract class Interactor<in P, R> {
    private var count: Int = 0
    private val loadingState = MutableStateFlow(false)

    val inProgress: Flow<Boolean>
        get() = loadingState.map { it }.distinctUntilChanged()

    private fun addLoader() {
        loadingState.value = true
    }

    private fun removeLoader() {
        loadingState.value = false
    }

    suspend operator fun invoke(
        params: P,
    ): Result<R> {
        return try {
            addLoader()
            doWork(params)
        } finally {
            removeLoader()
        }
    }

    protected abstract suspend fun doWork(params: P): Result<R>
}
