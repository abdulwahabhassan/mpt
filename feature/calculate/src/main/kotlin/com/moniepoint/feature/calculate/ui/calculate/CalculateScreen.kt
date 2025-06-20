package com.moniepoint.feature.calculate.ui.calculate

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moniepoint.core.designsystem.component.MoniePointFilledButton
import com.moniepoint.core.designsystem.component.MoniePointFilterChip
import com.moniepoint.core.designsystem.component.MoniePointInputField
import com.moniepoint.core.designsystem.component.MoniePointSelectionDialogMenu
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.enums.Category
import com.moniepoint.core.model.enums.Packaging
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun CalculateRoute(
    viewModel: CalculateScreenViewModel = hiltViewModel(),
    onTapBackIcon: () -> Unit,
    onGoToEstimateRoute: () -> Unit
) {
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()

    CalculateScreen(
        screenState = screenState,
        onTapBackIcon = onTapBackIcon,
        onUiEvent = { event: CalculateScreenEvent ->
            viewModel.sendEvent(event)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.oneShotState.onEach { oneShotState: CalculateScreenOneShotState ->
            when (oneShotState) {
                CalculateScreenOneShotState.GoToEstimateScreen -> onGoToEstimateRoute()
            }
        }.launchIn(this)
    }

    LaunchedEffect(Unit) {
        viewModel.sendEvent(CalculateScreenEvent.OnLoadUiData)
    }
}

@Composable
internal fun CalculateScreen(
    screenState: CalculateScreenState,
    onTapBackIcon: () -> Unit,
    onUiEvent: (CalculateScreenEvent) -> Unit,
) {
    BackHandler {
        onTapBackIcon()
    }

    var uiVisibility by remember { mutableStateOf(false) }

    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        content = {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        "Destination",
                        style = MaterialTheme.typography.headlineSmall.copy(color = DecorativeColor.black)
                    )
                    Card {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AnimatedVisibility(
                                visible = uiVisibility,
                                enter = slideInVertically { it },
                                exit = fadeOut()
                            ) {
                                MoniePointInputField(
                                    textFieldValue = screenState.senderLocationTextFieldValue,
                                    onTextFieldValueChange = { text ->
                                        onUiEvent(CalculateScreenEvent.OnInputSenderLocation(text))
                                    },
                                    placeholderText = "Sender location",
                                    leadingIcon = MoniePointIcons.Outbox,
                                )
                            }
                            AnimatedVisibility(
                                visible = uiVisibility,
                                enter = slideInVertically { it },
                                exit = fadeOut()
                            ) {
                                MoniePointInputField(
                                    textFieldValue = screenState.receiverLocationTextFieldValue,
                                    onTextFieldValueChange = { text ->
                                        onUiEvent(CalculateScreenEvent.OnInputReceiverLocation(text))
                                    },
                                    placeholderText = "Receiver location",
                                    leadingIcon = MoniePointIcons.MoveToInbox,
                                    horizontalPadding = 2.dp
                                )
                            }
                            AnimatedVisibility(
                                visible = uiVisibility,
                                enter = slideInVertically { it },
                                exit = fadeOut()
                            ) {
                                MoniePointInputField(
                                    textFieldValue = screenState.approxWeightTextFieldValue,
                                    onTextFieldValueChange = { text ->
                                        onUiEvent(CalculateScreenEvent.OnInputApproxWeight(text))
                                    },
                                    placeholderText = "Approx weight",
                                    leadingIcon = MoniePointIcons.Scale,
                                    horizontalPadding = 2.dp
                                )
                            }
                        }
                    }
                    AnimatedVisibility(
                        visible = uiVisibility,
                        enter = slideInVertically { it },
                        exit = fadeOut()
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                "Packaging",
                                style = MaterialTheme.typography.headlineSmall.copy(color = DecorativeColor.black)
                            )
                            Text(
                                "What are you sending?",
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Card {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    MoniePointSelectionDialogMenu(
                                        label = "Select Packaging",
                                        showLabel = false,
                                        placeholder = "",
                                        items = Packaging.entries,
                                        feedBack = "",
                                        onItemSelected = { _, item ->
                                            onUiEvent(CalculateScreenEvent.OnSelectPackaging(item))
                                        },
                                        isFieldMandatory = true,
                                        leadingIcon = painterResource(MoniePointIcons.Package),
                                        selectedIndex = Packaging.entries.indexOf(screenState.selectedPackaging)
                                    )
                                }
                            }
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Text(
                            "Categories",
                            style = MaterialTheme.typography.headlineSmall.copy(color = DecorativeColor.black)
                        )
                        Text(
                            "What are you sending?",
                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                AnimatedVisibility(
                    uiVisibility,
                    enter = slideInHorizontally(initialOffsetX = { it }),
                    exit = fadeOut()
                ) {
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalItemSpacing = 8.dp,
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(Category.entries) { category ->
                            MoniePointFilterChip(
                                title = category.name,
                                isSelected = category == screenState.selectedCategory,
                                onClick = { onUiEvent(CalculateScreenEvent.OnSelectCategory(category)) },
                                leadingIcon = MoniePointIcons.Check
                            )

                        }
                    }
                }
            }
//            item { Spacer(modifier = Modifier.height(40.dp)) }
            item {
                Spacer(modifier = Modifier.height(40.dp))
                AnimatedVisibility(
                    visible = uiVisibility,
                    enter = slideInVertically { it },
                    exit = fadeOut()
                ) {
                    MoniePointFilledButton(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Calculate",
                        onClick = {
                            onUiEvent(CalculateScreenEvent.OnTapCalculate)
                        },
                        backgroundColor = DecorativeColor.orange02,
                        shape = CircleShape
                    )
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
private fun CalculateScreenPreview() {
    MoniePointTheme {
        CalculateScreen(
            screenState = CalculateScreenState(selectedPackaging = Packaging.Box),
            onTapBackIcon = {},
            onUiEvent = {}
        )
    }
}