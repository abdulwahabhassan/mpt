package com.moniepoint.feature.shipment.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.model.enums.ShipmentStatus
import com.moniepoint.core.ui.components.ShipmentItem
import com.moniepoint.core.ui.components.ShipmentStatusFilterItem
import kotlinx.coroutines.delay

@Composable
internal fun ShipmentRoute(
    viewModel: ShipmentScreenViewModel = hiltViewModel(),
    onTapBackIcon: () -> Unit,
) {
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()

    ShipmentScreen(
        screenState = screenState,
        onTapBackIcon = onTapBackIcon,
        onUiEvent = { event: ShipmentScreenEvent ->
            viewModel.sendEvent(event)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ShipmentScreen(
    screenState: ShipmentScreenState,
    onTapBackIcon: () -> Unit,
    onUiEvent: (ShipmentScreenEvent) -> Unit,
) {
    BackHandler {
        onTapBackIcon()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            stickyHeader {
                LazyRow(
                    modifier = Modifier
                        .height(42.dp)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary),
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    items(listOf(null).plus(ShipmentStatus.entries.toList())) { filter ->
                        AnimatedVisibility(
                            screenState.filterVisibility,
                            enter = slideInHorizontally(
                                initialOffsetX = { it },
                            ) + fadeIn(),
                            exit = fadeOut()
                        ) {
                            ShipmentStatusFilterItem(
                                filter = filter,
                                count = if (filter == null) screenState.totalShipmentsCount else screenState.shipmentStatusCountMap[filter]
                                    ?: 0,
                                selected = filter == screenState.selectedShipmentStatusFilter,
                                onClick = {
                                    onUiEvent(
                                        ShipmentScreenEvent.OnSelectShipmentStatusFilter(
                                            filter
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
            item {
                AnimatedVisibility(
                    screenState.itemVisibility,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(delayMillis = 300)
                    ) + fadeIn(animationSpec = tween(delayMillis = 300)),
                    exit = fadeOut()
                ) {
                    Text(
                        "Shipments",
                        modifier = Modifier.padding(
                            top = 24.dp,
                            bottom = 12.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                        style = MaterialTheme.typography.headlineSmall.copy(color = DecorativeColor.black)
                    )
                }

            }
            itemsIndexed(screenState.shipments) { index, shipment ->
                var visible by remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(index * 100L)
                    visible = true
                }

                AnimatedVisibility(
                    visible = visible,
                    enter = slideInVertically(
                        initialOffsetY = { it },
                        animationSpec = tween(durationMillis = 300)
                    ) + fadeIn(animationSpec = tween(durationMillis = 300)),
                    exit = fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .animateItemPlacement()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ) {
                        ShipmentItem(shipment)
                    }
                }
            }
        },
    )

    LaunchedEffect(Unit) {
        onUiEvent(ShipmentScreenEvent.OnLoadUiData)
    }

}

@Composable
@Preview(showBackground = true)
private fun ShipmentScreenPreview() {
    MoniePointTheme {
        ShipmentScreen(
            screenState = ShipmentScreenState(
                _shipments = ShipmentEntity.shipments,
                filterVisibility = true
            ),
            onTapBackIcon = {},
            onUiEvent = {}
        )
    }
}