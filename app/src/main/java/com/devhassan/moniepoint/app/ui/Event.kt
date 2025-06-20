package com.devhassan.moniepoint.app.ui

import com.moniepoint.core.model.enums.ShipmentStatus

sealed interface MainActivityEvent {
    data class OnInputSearchQuery(val query: String) :
        MainActivityEvent

    data object OnLoadUiData : MainActivityEvent
    data class OnSelectShipmentStatusFilter(val filter: ShipmentStatus) : MainActivityEvent
}