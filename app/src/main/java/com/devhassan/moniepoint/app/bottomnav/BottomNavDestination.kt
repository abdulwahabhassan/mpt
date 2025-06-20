package com.devhassan.moniepoint.app.bottomnav

import androidx.compose.ui.graphics.vector.ImageVector
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.feature.calculate.navigation.calculateNavGraphRoute
import com.moniepoint.feature.home.navigation.homeNavGraphRoute
import com.moniepoint.feature.profile.navigation.profileNavGraphRoute
import com.moniepoint.feature.shipment.navigation.shipmentNavGraphRoute

enum class BottomNavDestination(val route: String, val icon: ImageVector, val label: String) {
    Home(homeNavGraphRoute, MoniePointIcons.Home, "Home"),
    Calculate(calculateNavGraphRoute, MoniePointIcons.Calculate, "Calculate"),
    Shipment(shipmentNavGraphRoute, MoniePointIcons.History, "Shipment"),
    Profile(profileNavGraphRoute, MoniePointIcons.Person, "Profile")
}