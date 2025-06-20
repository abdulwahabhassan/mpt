package com.moniepoint.feature.shipment.ui

import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.model.enums.ShipmentStatus
import com.moniepoint.core.ui.viewmodel.OneShotState

internal data class ShipmentScreenState(
    private val isFetchingData: Boolean = false,
    val selectedShipmentStatusFilter: ShipmentStatus? = null,
    private val _shipments: List<ShipmentEntity> = emptyList(),
    val filterVisibility: Boolean = false,
    val itemVisibility: Boolean = false,
) {
    val isLoading: Boolean
        get() = isFetchingData
    val isUserInputEnabled: Boolean
        get() = isLoading.not()
    val shipments: List<ShipmentEntity>
        get() = _shipments.filter { shipment ->
            selectedShipmentStatusFilter?.let { it == shipment.status } ?: true
        }
    val shipmentStatusCountMap: Map<ShipmentStatus, Int>
        get() = _shipments.groupBy { it.status }.mapValues { it.value.count() }
    val totalShipmentsCount: Int
        get() = _shipments.count()
}

internal sealed interface ShipmentScreenOneShotState : OneShotState