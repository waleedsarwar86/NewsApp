package com.criticaltechworks.news.core.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopHeadlinesRemote(
    @Json(name = "articles")
    val articleRemotes: List<ArticleRemote>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int,
)
