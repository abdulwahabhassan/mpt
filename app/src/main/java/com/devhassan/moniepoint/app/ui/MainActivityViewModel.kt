package com.devhassan.moniepoint.app.ui

import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainActivityViewModel @Inject constructor() :
    BaseViewModel<MainActivityEvent, MainActivityState, MainActivityOneShotState>(
        MainActivityState()
    ) {
    override fun handleUiEvents(event: MainActivityEvent) {
        when (event) {
            MainActivityEvent.OnLoadUiData -> {
                loadUiData()
            }

            is MainActivityEvent.OnInputSearchQuery -> {
                setUiState { copy(searchQuery = event.query) }
            }

            is MainActivityEvent.OnSelectShipmentStatusFilter -> {
                setUiState { copy(selectedShipmentStatusFilter = event.filter) }
            }
        }
    }

    private fun loadUiData() {
        setUiState {
            copy(
                _shipments = ShipmentEntity.shipments
            )
        }
    }

}