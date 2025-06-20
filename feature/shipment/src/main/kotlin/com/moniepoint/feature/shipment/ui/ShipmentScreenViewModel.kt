package com.moniepoint.feature.shipment.ui

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ShipmentScreenViewModel @Inject constructor() :
    BaseViewModel<ShipmentScreenEvent, ShipmentScreenState, ShipmentScreenOneShotState>(
        ShipmentScreenState()
    ) {
    override fun handleUiEvents(event: ShipmentScreenEvent) {
        when (event) {
            ShipmentScreenEvent.OnLoadUiData -> {
                loadUiData()
            }

            is ShipmentScreenEvent.OnSelectShipmentStatusFilter -> {
                setUiState {
                    copy(
                        itemVisibility = false,
                        selectedShipmentStatusFilter = event.shipmentStatusFilter,
                    )
                }
                viewModelScope.launch {
                    setUiState { copy(itemVisibility = true) }
                }
            }
        }
    }

    private fun loadUiData() {
        viewModelScope.launch {
            setUiState {
                copy(
                    _shipments = ShipmentEntity.shipments,
                    filterVisibility = true,
                )
            }
            setUiState { copy(itemVisibility = true) }
        }

    }

}