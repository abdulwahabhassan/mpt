package com.devhassan.moniepoint.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun rememberNavState(
    navHostController: NavHostController = rememberNavController(),
) = MoniePointAppNavState(
    navHostController,
)

@Stable
internal data class MoniePointAppNavState(
    val navHostController: NavHostController,
)