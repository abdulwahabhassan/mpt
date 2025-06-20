package com.moniepoint.core.data.repository.intf

import com.moniepoint.core.model.entity.ShipmentEntity

interface ShipmentRepository {
    fun getShipments(): List<ShipmentEntity>
}