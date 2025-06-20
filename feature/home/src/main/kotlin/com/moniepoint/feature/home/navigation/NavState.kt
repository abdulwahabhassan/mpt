package com.moniepoint.feature.home.navigation

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
): MutableState<HomeNavState> {
    return remember(
        navHostController,
    ) {
        mutableStateOf(
            HomeNavState(
                navHostController = navHostController,
            ),
        )
    }
}

@Stable
internal data class HomeNavState(
    val navHostController: NavHostController,
)
