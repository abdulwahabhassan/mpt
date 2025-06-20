package com.moniepoint.feature.profile.ui

import com.moniepoint.core.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ProfileScreenViewModel @Inject constructor() :
    BaseViewModel<ProfileScreenEvent, ProfileScreenState, ProfileScreenOneShotState>(
        ProfileScreenState()
    ) {
    override fun handleUiEvents(event: ProfileScreenEvent) {
        when (event) {
            ProfileScreenEvent.OnLoadUiData -> {}
        }
    }

    private fun loadUiData() {}

}