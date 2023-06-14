package com.criticaltechworks.news.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.criticaltechworks.news.app.ui.NewsApp
import com.criticaltechworks.news.app.ui.rememberNewsAppState
import com.criticaltechworks.news.core.auth.BiometricAuthenticationResult.Error
import com.criticaltechworks.news.core.auth.BiometricAuthenticationResult.Failure
import com.criticaltechworks.news.core.auth.BiometricAuthenticationResult.Success
import com.criticaltechworks.news.core.auth.BiometricAuthenticator
import com.criticaltechworks.news.core.design.theme.NewsAppTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var biometricAuthenticator: BiometricAuthenticator

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { viewModel.uiState.value.isAuthenticated.not() }
        authenticateWithBiometrics()
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

    private fun authenticateWithBiometrics() {
        if (viewModel.uiState.value.isAuthenticated.not()) {
            lifecycleScope.launch {
                biometricAuthenticator.authenticate(this@MainActivity).collect { result ->
                    when (result) {
                        is Success -> {
                            viewModel.setIsAuthenticated(true)
                        }
                        is Failure, is Error -> {
                            viewModel.setIsAuthenticated(false)
                        }
                    }
                }
            }
        }
    }
}
