package com.moniepoint.feature.home.ui

import com.moniepoint.core.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenViewModel @Inject constructor() :
    BaseViewModel<HomeScreenEvent, HomeScreenState, HomeScreenOneShotState>(
        HomeScreenState()
    ) {
    override fun handleUiEvents(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnInputSearchQuery -> {

            }
            HomeScreenEvent.OnLoadUiData -> {

            }
            HomeScreenEvent.OnTapSearchField -> {

            }
        }
    }

    private fun loadUiData() {}

}