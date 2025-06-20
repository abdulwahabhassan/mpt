package com.moniepoint.feature.calculate.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moniepoint.feature.calculate.ui.calculate.CalculateRoute

const val calculateNavGraphRoute = "calculate_nav_graph"
internal const val calculateNavRoute = calculateNavGraphRoute.plus("/calculate_route")
internal const val calculateRoute = calculateNavRoute.plus("/calculate_screen")

internal fun NavGraphBuilder.calculateRoute(
    onTapBackIcon: () -> Unit,
    onGoToEstimateRoute: () -> Unit
 ) {
    composable(route = calculateRoute) {
        CalculateRoute(
            onTapBackIcon = onTapBackIcon,
            onGoToEstimateRoute = onGoToEstimateRoute
        )
    }
}
