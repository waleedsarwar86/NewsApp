package com.criticaltechworks.news.app.appinitializers

import com.criticaltechworks.news.BuildConfig
import com.criticaltechworks.news.core.common.appinitializer.AppInitializer
import com.criticaltechworks.news.core.common.logger.Logger
import javax.inject.Inject

class TimberInitializer @Inject constructor(
    private val logger: Logger,
) : AppInitializer {
    override fun init() = logger.setup(BuildConfig.DEBUG)
}
