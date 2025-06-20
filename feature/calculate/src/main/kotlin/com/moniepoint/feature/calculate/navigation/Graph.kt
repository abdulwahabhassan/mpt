package com.moniepoint.feature.calculate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.calculateNavGraph(
    onTapBackIcon: () -> Unit,
    onGoToEstimateRoute: () -> Unit
) {
    navigation(
        route = calculateNavGraphRoute,
        startDestination = calculateNavRoute,
    ) {
        composable(calculateNavRoute) {
            val navState by rememberNavState()
            CalculateNavHost(
                navHostController = navState.navHostController,
                onTapBackIcon = onTapBackIcon,
                onGoToEstimateRoute = onGoToEstimateRoute
            )
        }
    }
}

@Composable
internal fun CalculateNavHost(
    navHostController: NavHostController,
    onTapBackIcon: () -> Unit,
    onGoToEstimateRoute: () -> Unit
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = calculateRoute,
    ) {
        calculateRoute(
            onTapBackIcon = onTapBackIcon,
            onGoToEstimateRoute = onGoToEstimateRoute
        )
    }
}
