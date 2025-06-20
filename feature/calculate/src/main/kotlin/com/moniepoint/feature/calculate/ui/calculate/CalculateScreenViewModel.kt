package com.moniepoint.feature.calculate.ui.calculate

import com.moniepoint.core.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CalculateScreenViewModel @Inject constructor() :
    BaseViewModel<CalculateScreenEvent, CalculateScreenState, CalculateScreenOneShotState>(
        CalculateScreenState()
    ) {
    override fun handleUiEvents(event: CalculateScreenEvent) {
        when (event) {
            is CalculateScreenEvent.OnInputApproxWeight -> {
                setUiState { copy(approxWeightTextFieldValue = event.approxWeightTextFieldValue) }
            }

            is CalculateScreenEvent.OnInputReceiverLocation -> {
                setUiState { copy(receiverLocationTextFieldValue = event.receiverLocationTextFieldValue) }
            }

            is CalculateScreenEvent.OnInputSenderLocation -> {
                setUiState { copy(senderLocationTextFieldValue = event.senderLocationTextFieldValue) }
            }

            CalculateScreenEvent.OnLoadUiData -> {
                loadUiData()
            }

            is CalculateScreenEvent.OnSelectCategory -> {
                setUiState { copy(selectedCategory = event.category) }
            }

            is CalculateScreenEvent.OnSelectPackaging -> {
                setUiState { copy(selectedPackaging = event.packaging) }
            }

            CalculateScreenEvent.OnTapCalculate -> {
                setOneShotState(CalculateScreenOneShotState.GoToEstimateScreen)
            }
        }
    }

    private fun loadUiData() {

    }

}