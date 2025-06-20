package com.moniepoint.feature.shipment.ui

import com.moniepoint.core.model.enums.ShipmentStatus

sealed interface ShipmentScreenEvent {
    data class OnSelectShipmentStatusFilter(val shipmentStatusFilter: ShipmentStatus?) :
        ShipmentScreenEvent

    data object OnLoadUiData : ShipmentScreenEvent
}