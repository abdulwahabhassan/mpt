package com.devhassan.moniepoint.app.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.moniepoint.feature.estimate.navigation.estimateNavGraphRoute
import com.moniepoint.feature.home.navigation.homeNavGraphRoute


internal fun NavHostController.navigateToHomeNavGraph(
    navOptions: NavOptions? = null,
) {
    this.navigate(homeNavGraphRoute, navOptions)
}

internal fun NavHostController.navigateToEstimateNavGraph(
    navOptions: NavOptions? = null,
) {
    this.navigate(estimateNavGraphRoute, navOptions)
}
