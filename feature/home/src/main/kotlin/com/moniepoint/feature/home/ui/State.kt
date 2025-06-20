package com.moniepoint.feature.home.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.moniepoint.core.ui.viewmodel.OneShotState

internal data class HomeScreenState(
    private val isFetchingData: Boolean = false,
    val searchQueryTextFieldValue: TextFieldValue = TextFieldValue(),
) {
    val isLoading: Boolean
        get() = isFetchingData
    val isUserInputEnabled: Boolean
        get() = isLoading.not()
}

internal sealed interface HomeScreenOneShotState : OneShotState