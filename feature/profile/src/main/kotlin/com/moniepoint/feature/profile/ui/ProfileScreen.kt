package com.moniepoint.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moniepoint.core.designsystem.component.MoniePointTitleBar
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
internal fun ProfileRoute(
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    onTapBackIcon: () -> Unit,
) {
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(
        screenState = screenState,
        onTapBackIcon = onTapBackIcon,
        onUiEvent = { event: ProfileScreenEvent ->
            viewModel.sendEvent(event)
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sendEvent(ProfileScreenEvent.OnLoadUiData)
    }
}

@Composable
internal fun ProfileScreen(
    screenState: ProfileScreenState,
    onTapBackIcon: () -> Unit,
    onUiEvent: (ProfileScreenEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            MoniePointTitleBar(
                onTapBackIcon = onTapBackIcon,
                title = "Profile",
            )
        },
    )
    { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(top = 24.dp),
            content = {
            },
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ProfileScreenPreview() {
    MoniePointTheme {
        ProfileScreen(
            screenState = ProfileScreenState(),
            onTapBackIcon = {},
            onUiEvent = {}
        )
    }
}