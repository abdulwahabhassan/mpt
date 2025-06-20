package com.moniepoint.feature.estimate.navigation

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
): MutableState<EstimateNavState> {
    return remember(
        navHostController,
    ) {
        mutableStateOf(
            EstimateNavState(
                navHostController = navHostController,
            ),
        )
    }
}

@Stable
internal data class EstimateNavState(
    val navHostController: NavHostController,
)
