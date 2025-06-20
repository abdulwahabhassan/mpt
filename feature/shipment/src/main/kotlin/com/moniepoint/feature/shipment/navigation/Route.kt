package com.moniepoint.feature.shipment.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moniepoint.feature.shipment.ui.ShipmentRoute

const val shipmentNavGraphRoute = "shipment_nav_graph"
internal const val shipmentNavRoute = shipmentNavGraphRoute.plus("/shipment_route")
internal const val shipmentRoute = shipmentNavRoute.plus("/shipment_screen")

internal fun NavGraphBuilder.shipmentRoute(
    onTapBackIcon: () -> Unit,
) {
    composable(route = shipmentRoute) {
        ShipmentRoute(
            onTapBackIcon = onTapBackIcon,
        )
    }
}

