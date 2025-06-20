package com.moniepoint.feature.profile.ui

import com.moniepoint.core.model.enums.Packaging
import com.moniepoint.core.ui.viewmodel.OneShotState

internal data class ProfileScreenState(
    private val isFetchingData: Boolean = false,
) {
    val isLoading: Boolean
        get() = isFetchingData
    val isUserInputEnabled: Boolean
        get() = isLoading.not()
}

internal sealed interface ProfileScreenOneShotState : OneShotState