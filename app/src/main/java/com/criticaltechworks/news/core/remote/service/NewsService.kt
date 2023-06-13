package com.criticaltechworks.news.core.remote.service

import com.criticaltechworks.news.core.remote.models.TopHeadlinesRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(@Query("sources") source: String): Result<TopHeadlinesRemote>
}
