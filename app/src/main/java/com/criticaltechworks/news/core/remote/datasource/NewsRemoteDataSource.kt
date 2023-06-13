package com.criticaltechworks.news.core.remote.datasource

import com.criticaltechworks.news.core.remote.models.TopHeadlinesRemote
import com.criticaltechworks.news.core.remote.service.NewsService
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val newsService: NewsService) {
    suspend fun fetchTopHeadlines(source: String): Result<TopHeadlinesRemote> {
        return newsService.getTopHeadlines(source)
    }
}
