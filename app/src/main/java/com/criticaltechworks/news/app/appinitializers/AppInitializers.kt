package com.criticaltechworks.news.app.appinitializers

import com.criticaltechworks.news.core.common.appinitializer.AppInitializer
import javax.inject.Inject

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>,
) {
    fun init() {
        initializers.forEach {
            it.init()
        }
    }
}
