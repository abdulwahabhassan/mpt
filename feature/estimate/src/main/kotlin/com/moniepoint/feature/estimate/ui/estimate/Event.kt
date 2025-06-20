package com.moniepoint.feature.estimate.ui.estimate

sealed interface EstimateScreenEvent {
    data object OnTapBackToHome : EstimateScreenEvent

    data object OnLoadUiData : EstimateScreenEvent
}