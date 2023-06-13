package com.criticaltechworks.news.app.ui

import android.os.Bundle
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberNewsAppState(
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    navController: NavHostController = rememberNavController(),
): NewsAppState {
    return remember(navController) {
        NewsAppState(
            navController = navController,
        )
    }
}

@Stable
class NewsAppState
constructor(
    val navController: NavHostController,
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
