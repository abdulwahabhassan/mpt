package com.moniepoint.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.homeNavGraph(
    onTapBackIcon: () -> Unit,
) {
    navigation(
        route = homeNavGraphRoute,
        startDestination = homeNavRoute,
    ) {
        composable(homeNavRoute) {
            val navState by rememberNavState()
            HomeNavHost(
                navHostController = navState.navHostController,
                onTapBackIcon = onTapBackIcon,
            )
        }
    }
}

@Composable
internal fun HomeNavHost(
    navHostController: NavHostController,
    onTapBackIcon: () -> Unit,
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = homeRoute,
    ) {
        homeRoute(
            onTapBackIcon = onTapBackIcon,
        )
    }
}
