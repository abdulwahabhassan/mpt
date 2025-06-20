package com.moniepoint.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.enums.Vehicle
import com.moniepoint.core.ui.components.AvailableVehicleItem
import com.moniepoint.core.ui.components.TrackingCard

@Composable
internal fun HomeRoute(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onTapBackIcon: () -> Unit,
) {
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        screenState = screenState,
        onTapBackIcon = onTapBackIcon,
        onUiEvent = { event: HomeScreenEvent ->
            viewModel.sendEvent(event)
        }
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.sendEvent(HomeScreenEvent.OnLoadUiData)
    }
}

@Composable
internal fun HomeScreen(
    screenState: HomeScreenState,
    onTapBackIcon: () -> Unit,
    onUiEvent: (HomeScreenEvent) -> Unit,
) {
    var uiVisibility by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        content = {
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "Tracking",
                        style = MaterialTheme.typography.headlineSmall.copy(color = DecorativeColor.black)
                    )
                    TrackingCard()
                }
            }
            item {
                Column {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Availble vehicles",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.headlineSmall.copy(color = DecorativeColor.black)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    items(Vehicle.entries) { vehicle: Vehicle ->
                        AvailableVehicleItem(vehicle, isVisible = uiVisibility)

                    }
                }
            }
        },
    )
    LaunchedEffect(Unit) {
        uiVisibility = true
    }
}

@Composable
@Preview(showBackground = true)
private fun HomeScreenPreview() {
    MoniePointTheme {
        HomeScreen(
            screenState = HomeScreenState(),
            onTapBackIcon = {},
            onUiEvent = {}
        )
    }
}