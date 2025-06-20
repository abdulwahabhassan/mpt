package com.moniepoint.core.model.entity

import com.moniepoint.core.model.enums.ShipmentStatus
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime

data class ShipmentEntity(
    val title: String,
    val shipmentId: String,
    val status: ShipmentStatus,
    val deliveryDate: LocalDateTime,
    val createdDate: LocalDateTime,
    val origin: String,
    val destination: String,
    val fee: Double
) {
    companion object {
        val shipments = listOf(
            ShipmentEntity(
                "Summer linen jacket",
                "#NEJ39392903992",
                ShipmentStatus.InProgress,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Barcelona",
                destination = "Morroco",
                fee = 450.00
            ),
            ShipmentEntity(
                "Tapered-fit jeans AW",
                "#NE3630002029",
                ShipmentStatus.PendingOrder,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Paris",
                destination = "Tokyo",
                fee = 10002.00
            ),
            ShipmentEntity(
                "Shoes",
                "#NE93020030933",
                ShipmentStatus.PendingOrder,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Thailand",
                destination = "Hong Kong",
                fee = 10002.00
            ),
            ShipmentEntity(
                "Groceries",
                "#NEJ890203772",
                ShipmentStatus.Loading,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Seychelles",
                destination = "Mauritius",
                fee = 900.00
            ),
            ShipmentEntity(
                "24k gold",
                "#NEJ00028993233",
                ShipmentStatus.Completed,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Kaduna",
                destination = "Abuja",
                fee = 56000.00
            ),
            ShipmentEntity(
                "Macbook pro M2",
                "#NE83920038222",
                ShipmentStatus.Cancelled,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Abuja",
                destination = "Lagos",
                fee = 172.00
            ),
            ShipmentEntity(
                "Office setup desk",
                "#NEJ39392903992",
                ShipmentStatus.InProgress,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "France",
                destination = "Germany",
                fee = 70.00
            ),
            ShipmentEntity(
                "Canva",
                "#NEJ0002893992",
                ShipmentStatus.InProgress,
                deliveryDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                createdDate = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
                origin = "Taiwan",
                destination = "Thailand",
                fee = 120.00
            ),
        )
    }
}
