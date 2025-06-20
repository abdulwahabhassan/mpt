package com.moniepoint.feature.estimate.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moniepoint.feature.estimate.ui.estimate.EstimateRoute

const val estimateNavGraphRoute = "estimate_nav_graph"
internal const val estimateNavRoute = estimateNavGraphRoute.plus("/estimate_route")
internal const val estimateRoute = estimateNavRoute.plus("/estimate_screen")


internal fun NavGraphBuilder.estimateRoute(
    onGoToHome: () -> Unit,
) {
    composable(route = estimateRoute) {
        EstimateRoute(onGoToHome = onGoToHome)
    }
}
