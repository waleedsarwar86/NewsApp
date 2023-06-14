package com.criticaltechworks.news.core.data.ext

import com.criticaltechworks.news.core.domain.model.Article
import com.criticaltechworks.news.core.remote.models.ArticleRemote

fun ArticleRemote.asArticle() = Article(
    image = urlToImage,
    title = title,
    description = description,
    content = content,
    publishedAt = publishedAt
)
