package com.moniepoint.core.data.datastore

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val userLocation: String = "Wertheimer, Illinois",
)
