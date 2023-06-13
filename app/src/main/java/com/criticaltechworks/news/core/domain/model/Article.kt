package com.criticaltechworks.news.core.domain.model

import java.time.LocalDateTime

data class Article(
    val title: String,
    val image: String?,
    val publishedAt: LocalDateTime,
)
