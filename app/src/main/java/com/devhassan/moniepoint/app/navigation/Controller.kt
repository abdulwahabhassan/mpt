package com.devhassan.moniepoint.app.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.moniepoint.feature.calculate.navigation.calculateNavGraphRoute
import com.moniepoint.feature.estimate.navigation.estimateNavGraphRoute
import com.moniepoint.feature.home.navigation.homeNavGraphRoute
import com.moniepoint.feature.profile.navigation.profileNavGraphRoute
import com.moniepoint.feature.shipment.navigation.shipmentNavGraphRoute


internal fun NavHostController.navigateToHomeNavGraph(
    navOptions: NavOptions? = null,
) {
    this.navigate(homeNavGraphRoute, navOptions)
}

internal fun NavHostController.navigateToCalculateNavGraph(
    navOptions: NavOptions? = null,
) {
    this.navigate(calculateNavGraphRoute, navOptions)
}

internal fun NavHostController.navigateToEstimateNavGraph(
    navOptions: NavOptions? = null,
) {
    this.navigate(estimateNavGraphRoute, navOptions)
}

internal fun NavHostController.navigateToShipmentNavGraph(
    navOptions: NavOptions? = null,
) {
    this.navigate(shipmentNavGraphRoute, navOptions)
}

internal fun NavHostController.navigateToProfileNavGraph(
    navOptions: NavOptions? = null
) {
    this.navigate(profileNavGraphRoute, navOptions)
}
