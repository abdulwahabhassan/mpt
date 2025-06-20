package com.moniepoint.feature.shipment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.shipmentNavGraph(
    onTapBackIcon: () -> Unit,
) {
    navigation(
        route = shipmentNavGraphRoute,
        startDestination = shipmentNavRoute,
    ) {
        composable(shipmentNavRoute) {
            val navState by rememberNavState()
            ShipmentNavHost(
                navHostController = navState.navHostController,
                onTapBackIcon = onTapBackIcon,
            )
        }
    }
}

@Composable
internal fun ShipmentNavHost(
    navHostController: NavHostController,
    onTapBackIcon: () -> Unit,
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = shipmentRoute,
    ) {
        shipmentRoute(
            onTapBackIcon = onTapBackIcon,
        )
    }
}
