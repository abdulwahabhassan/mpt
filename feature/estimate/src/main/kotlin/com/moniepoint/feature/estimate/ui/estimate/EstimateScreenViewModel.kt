package com.moniepoint.feature.estimate.ui.estimate

import com.moniepoint.core.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EstimateScreenViewModel @Inject constructor() :
    BaseViewModel<EstimateScreenEvent, EstimateScreenState, EstimateScreenOneShotState>(
        EstimateScreenState()
    ) {
    override fun handleUiEvents(event: EstimateScreenEvent) {
        when (event) {
            EstimateScreenEvent.OnLoadUiData -> {

            }

            EstimateScreenEvent.OnTapBackToHome -> {
                setOneShotState(EstimateScreenOneShotState.GoToHome)
            }
        }
    }

    private fun loadUiData() {}

}