package com.moniepoint.feature.shipment.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun rememberNavState(
    navHostController: NavHostController = rememberNavController(),
): MutableState<ShipmentNavState> {
    return remember(
        navHostController,
    ) {
        mutableStateOf(
            ShipmentNavState(
                navHostController = navHostController,
            ),
        )
    }
}

@Stable
internal data class ShipmentNavState(
    val navHostController: NavHostController,
)
