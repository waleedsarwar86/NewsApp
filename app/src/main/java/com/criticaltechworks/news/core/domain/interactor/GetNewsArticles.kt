package com.criticaltechworks.news.core.domain.interactor

import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsArticles @Inject constructor(private val newsRepository: NewsRepository) :
    Interactor<String, List<Article>>() {
    override suspend fun doWork(params: String): Result<List<Article>> {
        return newsRepository.fetchArticles(params)
    }
}
