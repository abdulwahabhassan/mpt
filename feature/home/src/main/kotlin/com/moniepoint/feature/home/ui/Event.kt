package com.moniepoint.feature.home.ui

import androidx.compose.ui.text.input.TextFieldValue

sealed interface HomeScreenEvent {
    data class OnInputSearchQuery(val searchQueryTextFieldValue: TextFieldValue) :
        HomeScreenEvent

    data object OnTapSearchField :
        HomeScreenEvent

    data object OnLoadUiData : HomeScreenEvent
}