package com.devhassan.moniepoint.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.devhassan.moniepoint.app.bottomnav.BottomNavDestination
import com.moniepoint.feature.calculate.navigation.calculateNavGraph
import com.moniepoint.feature.estimate.navigation.estimateNavGraph
import com.moniepoint.feature.home.navigation.homeNavGraph
import com.moniepoint.feature.profile.navigation.profileNavGraph
import com.moniepoint.feature.shipment.navigation.shipmentNavGraph

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    modifier: Modifier,
    onTapBackIcon: () -> Unit,
) {
    AppNavHost(
        modifier = modifier,
        navHostController = navHostController,
        onTapBackIcon = onTapBackIcon,
    )
}

@Composable
internal fun AppNavHost(
    modifier: Modifier,
    navHostController: NavHostController,
    onTapBackIcon: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = BottomNavDestination.Home.route,
    ) {
        homeNavGraph(
            onTapBackIcon = onTapBackIcon,
        )
        calculateNavGraph(
            onTapBackIcon = { navHostController.navigateToHomeNavGraph(navOptions {
                this.launchSingleTop = true
            }) },
            onGoToEstimateRoute = {
                navHostController.navigateToEstimateNavGraph()
            }
        )
        estimateNavGraph(
            onGoToHome = { navHostController.navigateToHomeNavGraph() }
        )
        shipmentNavGraph(
            onTapBackIcon = { navHostController.navigateToHomeNavGraph() },
        )
        profileNavGraph(
            onTapBackIcon = { navHostController.navigateToHomeNavGraph() },
        )
    }
}