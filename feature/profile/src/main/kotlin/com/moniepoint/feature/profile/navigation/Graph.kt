package com.moniepoint.feature.profile.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.profileNavGraph(
    onTapBackIcon: () -> Unit,
) {
    navigation(
        route = profileNavGraphRoute,
        startDestination = profileNavRoute,
    ) {
        composable(profileNavRoute) {
            val navState by rememberNavState()
            ProfileNavHost(
                navHostController = navState.navHostController,
                onTapBackIcon = onTapBackIcon,
            )
        }
    }
}

@Composable
internal fun ProfileNavHost(
    navHostController: NavHostController,
    onTapBackIcon: () -> Unit,
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = profileRoute,
    ) {
        profileRoute(
            onTapBackIcon = onTapBackIcon,
        )
    }
}
