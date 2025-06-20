package com.moniepoint.feature.profile.navigation

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
): MutableState<ProfileNavState> {
    return remember(
        navHostController,
    ) {
        mutableStateOf(
            ProfileNavState(
                navHostController = navHostController,
            ),
        )
    }
}

@Stable
internal data class ProfileNavState(
    val navHostController: NavHostController,
)
