package com.moniepoint.feature.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moniepoint.feature.profile.ui.ProfileRoute

const val profileNavGraphRoute = "profile_nav_graph"
internal const val profileNavRoute = profileNavGraphRoute.plus("/profile_route")
internal const val profileRoute = profileNavRoute.plus("/profile_screen")

internal fun NavGraphBuilder.profileRoute(
    onTapBackIcon: () -> Unit,
) {
    composable(route = profileRoute) {
        ProfileRoute(
            onTapBackIcon = onTapBackIcon,
        )
    }
}

