package com.criticaltechworks.news.core.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val newsAppDispatchers: NewsAppDispatchers)

enum class NewsAppDispatchers {
    IO,
}
