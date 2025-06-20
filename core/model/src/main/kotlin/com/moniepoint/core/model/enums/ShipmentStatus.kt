package com.moniepoint.core.model.enums

enum class ShipmentStatus(val label: String, val filterTitle: String) {
    Completed("completed", "Completed"),
    InProgress("in-progress", "In progress"),
    PendingOrder("pending", "Pending"),
    Loading("loading", "Loading"),
    Cancelled("cancelled", "Cancelled")
}