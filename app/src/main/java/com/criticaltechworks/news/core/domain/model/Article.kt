package com.criticaltechworks.news.core.domain.model

import java.time.LocalDateTime

data class Article(
    val image: String?,
    val title: String,
    val description:String?,
    val content: String?,
    val publishedAt: LocalDateTime,
)
