package com.moniepoint.feature.estimate.ui.estimate

import com.moniepoint.core.ui.viewmodel.OneShotState

internal data class EstimateScreenState(
    private val isEstimatingAmount: Boolean = false,
    val estimatedAmount: Double = 1460.00,
) {
    val isLoading: Boolean
        get() = isEstimatingAmount
    val isUserInputEnabled: Boolean
        get() = isLoading.not()
}

internal sealed interface EstimateScreenOneShotState : OneShotState {
    data object GoToHome : EstimateScreenOneShotState
}