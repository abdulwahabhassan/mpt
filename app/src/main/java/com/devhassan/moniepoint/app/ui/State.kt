package com.devhassan.moniepoint.app.ui

import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.model.enums.ShipmentStatus
import com.moniepoint.core.ui.viewmodel.OneShotState

internal data class MainActivityState(
    private val isFetchingData: Boolean = false,
    val selectedShipmentStatusFilter: ShipmentStatus = ShipmentStatus.InProgress,
    private val _shipments: List<ShipmentEntity> = emptyList(),
    val searchQuery: String = ""
) {
    val isLoading: Boolean
        get() = isFetchingData
    val isUserInputEnabled: Boolean
        get() = isLoading.not()
    val shipments: List<ShipmentEntity>
        get() = _shipments.filter {
            it.shipmentId.contains(searchQuery, ignoreCase = true) || it.fee.toString()
                .contains(
                    searchQuery,
                    ignoreCase = true
                ) || it.origin.contains(searchQuery) || it.destination.contains(
                searchQuery, ignoreCase = true
            ) || it.createdDate.toString()
                .contains(searchQuery, ignoreCase = true) || it.deliveryDate.toString()
                .contains(searchQuery, ignoreCase = true) || it.status.label.contains(
                searchQuery,
                ignoreCase = true
            )
        }
}

internal sealed interface MainActivityOneShotState : OneShotState