package com.moniepoint.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moniepoint.feature.home.ui.HomeRoute

const val homeNavGraphRoute = "home_nav_graph"
internal const val homeNavRoute = homeNavGraphRoute.plus("/home_route")
internal const val homeRoute = homeNavRoute.plus("/home_screen")

internal fun NavGraphBuilder.homeRoute(
    onTapBackIcon: () -> Unit,
) {
    composable(route = homeRoute) {
        HomeRoute (
            onTapBackIcon = onTapBackIcon,
        )
    }
}

