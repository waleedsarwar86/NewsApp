package com.criticaltechworks.news.core.domain.repository

import com.criticaltechworks.news.core.domain.model.Article

interface NewsRepository {
    suspend fun fetchArticles(source: String): Result<List<Article>>
}
