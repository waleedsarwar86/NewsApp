package com.criticaltechworks.news.app

import android.app.Application
import com.criticaltechworks.news.app.appinitializers.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application() {
    @Inject
    lateinit var initializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        initializers.init()
    }
}
