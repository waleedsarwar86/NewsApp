package com.criticaltechworks.news.core.domain.model

import java.time.LocalDateTime

data class Article(
    val image: String? = null,
    val title: String,
    val description: String? = null,
    val content: String? = null,
    val publishedAt: LocalDateTime,
)
