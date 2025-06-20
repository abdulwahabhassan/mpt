package com.moniepoint.feature.estimate.ui.estimate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moniepoint.core.designsystem.component.MoniePointFilledButton
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.feature.estimate.ui.AnimatedCountingNumber

@Composable
internal fun EstimateRoute(
    viewModel: EstimateScreenViewModel = hiltViewModel(),
    onGoToHome: () -> Unit
) {
    val screenState by viewModel.uiState.collectAsStateWithLifecycle()

    EstimateScreen(
        screenState = screenState,
        onUiEvent = { event: EstimateScreenEvent ->
            viewModel.sendEvent(event)
        }
    )
    LaunchedEffect(Unit) {
        viewModel.oneShotState.collect { state ->
            when (state) {
                EstimateScreenOneShotState.GoToHome -> onGoToHome()
            }

        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.sendEvent(EstimateScreenEvent.OnLoadUiData)
    }

}

@Composable
internal fun EstimateScreen(
    screenState: EstimateScreenState,
    onUiEvent: (EstimateScreenEvent) -> Unit,
) {
    var iconVisibility by remember { mutableStateOf(false) }
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        content = {
            item { }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "MoveMate",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 32.sp
                            ),
                            fontStyle = FontStyle.Italic
                        )
                        Icon(
                            painter = painterResource(MoniePointIcons.MoveMate),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimatedVisibility(visible = iconVisibility, enter = scaleIn()) {
                        Icon(
                            painter = painterResource(MoniePointIcons.Package),
                            null,
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                                .size(128.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "Total Estimated Amount",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp)
                                .copy(color = DecorativeColor.black),

                            )
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            AnimatedCountingNumber(screenState.estimatedAmount)
                            Text(
                                "USD",
                                style = MaterialTheme.typography.bodyMedium.copy(color = DecorativeColor.green02),
                            )
                        }

                        Text(
                            "This amount is estimated, this will vary if you change your location or weight",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.tertiary,
                                lineHeight = 16.sp
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    MoniePointFilledButton(
                        modifier = Modifier.padding(16.dp),
                        text = "Back to home",
                        onClick = {
                            onUiEvent(EstimateScreenEvent.OnTapBackToHome)
                        },
                        backgroundColor = DecorativeColor.orange02,
                        shape = CircleShape
                    )
                }
            }
            item { }
        },
    )

    LaunchedEffect(Unit) {
        iconVisibility = true
    }
}

@Composable
@Preview(showBackground = true)
private fun EstimateScreenPreview() {
    MoniePointTheme {
        EstimateScreen(
            screenState = EstimateScreenState(),
            onUiEvent = {}
        )
    }
}