package com.criticaltechworks.news.core.data.repository

import com.criticaltechworks.news.core.data.ext.asArticle
import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.domain.repository.NewsRepository
import com.criticaltechworks.news.core.remote.datasource.NewsRemoteDataSource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val remoteDataSource: NewsRemoteDataSource) :
    NewsRepository {
    override suspend fun fetchArticles(source: String): Result<List<Article>> {
        return remoteDataSource.fetchTopHeadlines(source)
            .map { headlinesRemote -> headlinesRemote.articleRemotes.map { it.asArticle() } }
    }
}
