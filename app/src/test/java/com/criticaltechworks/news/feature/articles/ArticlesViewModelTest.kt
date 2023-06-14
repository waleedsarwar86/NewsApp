package com.criticaltechworks.news.feature.articles

import com.criticaltechworks.news.BuildConfig
import com.criticaltechworks.news.core.common.logger.Logger
import com.criticaltechworks.news.core.domain.interactor.GetNewsArticles
import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.test_utils.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime

class ArticlesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    private lateinit var getNewsArticles: GetNewsArticles

    @RelaxedMockK
    private lateinit var logger: Logger

    private lateinit var viewModel: ArticlesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should set inProgress flag to true while fetching articles`() = runTest {
        mockGetNewsArticlesInProgress()
        mockGetNewsArticlesSuccess()

        viewModel = initViewModel()

        val collectJob = launch { viewModel.uiState.collect {} }

        advanceTimeBy(500)
        assert(viewModel.uiState.value.isLoading)

        collectJob.cancel()
    }

    @Test
    fun `should update articles on successful API response`() = runTest {
        mockGetNewsArticlesInProgress()
        mockGetNewsArticlesSuccess()

        viewModel = ArticlesViewModel(BuildConfig.NEWS_SOURCE, getNewsArticles, logger)

        val collectJob =
            launch { viewModel.uiState.collect {} }
        advanceTimeBy(500)
        assert(viewModel.uiState.value.articles == articles)
        verify { logger.i(articles.toString()) }
        collectJob.cancel()
    }

    @Test
    fun `should emit UI message on API failure`() = runTest {
        mockGetNewsArticlesInProgress()
        mockGetNewsArticlesFailure()

        viewModel = initViewModel()

        val collectJob = launch { viewModel.uiState.collect {} }
        advanceTimeBy(500)
        assert(viewModel.uiState.value.message?.message == errorMessage)
        verify { logger.i(throwable) }
        collectJob.cancel()
    }

    @Test
    fun `should update selected article index and set article details open flag`() = runTest {

        mockGetNewsArticlesInProgress()
        mockGetNewsArticlesSuccess()

        viewModel = initViewModel()

        val collectJob = launch { viewModel.uiState.collect {} }
        viewModel.handleEvent(ArticlesUiEvent.SetSelectedArticleIndex(1))

        advanceTimeBy(500)
        assert(viewModel.uiState.value.selectedArticleIndex == 1)
        assert(viewModel.uiState.value.isArticleDetailOpen)
        collectJob.cancel()
    }

    @Test
    fun `should update article details open flag`() = runTest {

        mockGetNewsArticlesInProgress()
        mockGetNewsArticlesSuccess()

        viewModel = initViewModel()

        val collectJob = launch { viewModel.uiState.collect {} }
        viewModel.handleEvent(ArticlesUiEvent.SetIsArticleDetailsOpen(true))
        advanceTimeBy(500)
        assert(viewModel.uiState.value.isArticleDetailOpen)
        collectJob.cancel()
    }

    private fun initViewModel(): ArticlesViewModel {

        return ArticlesViewModel(BuildConfig.NEWS_SOURCE, getNewsArticles, logger)
    }

    private fun mockGetNewsArticlesInProgress() {
        every { getNewsArticles.inProgress } returns flowOf(true)
    }

    private fun mockGetNewsArticlesSuccess() {
        coEvery { getNewsArticles(BuildConfig.NEWS_SOURCE) } coAnswers {
            Result.success(articles)
        }
    }


    private fun mockGetNewsArticlesFailure() {
        coEvery { getNewsArticles(BuildConfig.NEWS_SOURCE) } coAnswers { Result.failure(throwable) }
    }

    companion object {
        const val errorMessage = "API failed"
        val throwable = Throwable(errorMessage)
        val articles = listOf(
            Article(
                image = "image",
                title = "title",
                description = "description",
                content = "content",
                publishedAt = LocalDateTime.of(2021, 1, 1, 1, 1),
            ),
        )
    }
}