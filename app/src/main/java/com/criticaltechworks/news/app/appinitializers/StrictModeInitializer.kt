package com.criticaltechworks.news.app.appinitializers

import android.os.StrictMode
import com.criticaltechworks.news.BuildConfig
import com.criticaltechworks.news.core.common.appinitializer.AppInitializer
import javax.inject.Inject

class StrictModeInitializer @Inject constructor() : AppInitializer {
    override fun init() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites()
                    .detectNetwork().penaltyLog().build(),
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder().detectActivityLeaks()
                    .detectLeakedRegistrationObjects().detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects().penaltyLog().build(),
            )
        }
    }
}
