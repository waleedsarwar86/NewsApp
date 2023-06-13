package com.criticaltechworks.news.core.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourceRemote(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
)
