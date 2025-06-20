package com.moniepoint.feature.estimate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.estimateNavGraph(
    onGoToHome: () -> Unit,
) {
    navigation(
        route = estimateNavGraphRoute,
        startDestination = estimateNavRoute,
    ) {
        composable(estimateNavRoute) {
            val navState by rememberNavState()
            EstimateNavHost(
                navHostController = navState.navHostController,
                onGoToHome = onGoToHome,
            )
        }
    }
}

@Composable
internal fun EstimateNavHost(
    navHostController: NavHostController,
    onGoToHome: () -> Unit,
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = estimateRoute,
    ) {
       estimateRoute(
            onGoToHome = onGoToHome,
        )
    }
}
