package com.moniepoint.feature.calculate.ui.calculate

import androidx.compose.ui.text.input.TextFieldValue
import com.moniepoint.core.model.enums.Category
import com.moniepoint.core.model.enums.Packaging

sealed interface CalculateScreenEvent {
    data class OnInputSenderLocation(val senderLocationTextFieldValue: TextFieldValue) :
        CalculateScreenEvent

    data class OnInputReceiverLocation(val receiverLocationTextFieldValue: TextFieldValue) :
        CalculateScreenEvent

    data class OnInputApproxWeight(val approxWeightTextFieldValue: TextFieldValue) :
        CalculateScreenEvent

    data class OnSelectPackaging(val packaging: Packaging) :
        CalculateScreenEvent

    data class OnSelectCategory(val category: Category) :
        CalculateScreenEvent

    data object OnTapCalculate : CalculateScreenEvent

    data object OnLoadUiData : CalculateScreenEvent
}