package com.criticaltechworks.news.app.ui

import android.os.Bundle
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature

@Composable
fun rememberNewsAppState(
    navController: NavHostController = rememberNavController(),
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
): NewsAppState {
    return remember(navController) {
        NewsAppState(
            windowSizeClass = windowSizeClass,
            displayFeatures = displayFeatures,
            navController = navController,
        )
    }
}

@Stable
class NewsAppState
constructor(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass,
    val displayFeatures: List<DisplayFeature>,
) {
    val currentNavDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val navArgs: Bundle?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.arguments

    val navStartDestination: String?
        get() = navController
            .graph.startDestinationRoute
}
