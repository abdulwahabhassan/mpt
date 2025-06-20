package com.moniepoint.core.data.repository.impl

import com.moniepoint.core.data.datastore.UserPreferencesDataStore
import com.moniepoint.core.data.di.IODispatcher
import com.moniepoint.core.data.repository.intf.ShipmentRepository
import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.network.connectivity.NetworkMonitor
import com.moniepoint.core.network.service.MoniePointService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Inject


class DefaultShipmentRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val networkMonitor: NetworkMonitor,
    private val json: Json,
    private val apiService: MoniePointService,
    private val dataStore: UserPreferencesDataStore,
) : ShipmentRepository {
    override fun getShipments(): List<ShipmentEntity> {
        TODO("Not yet implemented")
    }

}