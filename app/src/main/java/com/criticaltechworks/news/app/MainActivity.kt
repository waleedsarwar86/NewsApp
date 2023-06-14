package com.criticaltechworks.news.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.criticaltechworks.news.app.ui.NewsApp
import com.criticaltechworks.news.app.ui.rememberNewsAppState
import com.criticaltechworks.news.core.design.theme.NewsAppTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.uiState.value.isLoading
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val appState = rememberNewsAppState(
                windowSizeClass = calculateWindowSizeClass(this),
                displayFeatures = calculateDisplayFeatures(this),
            )
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            NewsAppTheme {
                NewsApp(
                    startDestination = uiState.startDestination,
                    appState = appState,
                )
            }
        }
    }
}
