package com.moniepoint.feature.profile.ui

sealed interface ProfileScreenEvent {
    data object OnLoadUiData : ProfileScreenEvent
}