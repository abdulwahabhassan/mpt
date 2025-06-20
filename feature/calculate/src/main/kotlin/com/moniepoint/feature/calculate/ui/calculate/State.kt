package com.moniepoint.feature.calculate.ui.calculate

import androidx.compose.ui.text.input.TextFieldValue
import com.moniepoint.core.model.enums.Category
import com.moniepoint.core.model.enums.Packaging
import com.moniepoint.core.ui.viewmodel.OneShotState

internal data class CalculateScreenState(
    private val isFetchingData: Boolean = false,
    val senderLocationTextFieldValue: TextFieldValue = TextFieldValue(),
    val receiverLocationTextFieldValue: TextFieldValue = TextFieldValue(),
    val approxWeightTextFieldValue: TextFieldValue = TextFieldValue(),
    val selectedPackaging: Packaging = Packaging.Box,
    val selectedCategory: Category? = null
) {
    val isLoading: Boolean
        get() = isFetchingData
    val isUserInputEnabled: Boolean
        get() = isLoading.not()
}

internal sealed interface CalculateScreenOneShotState : OneShotState {
    data object GoToEstimateScreen : CalculateScreenOneShotState
}