package com.devhassan.moniepoint.app.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.devhassan.moniepoint.app.bottomnav.BottomNavDestination
import com.devhassan.moniepoint.app.bottomnav.BottomNavigationBar
import com.devhassan.moniepoint.app.navigation.AppNavGraph
import com.devhassan.moniepoint.app.navigation.navigateToHomeNavGraph
import com.devhassan.moniepoint.app.navigation.rememberNavState
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.entity.ShipmentEntity
import com.moniepoint.core.ui.components.SearchItem

@Composable
internal fun MoniePointApp(
    viewModel: MainActivityViewModel = hiltViewModel(),
    onExitApp: () -> Unit,
) {
    val navState = rememberNavState()
    val currentBackStackEntry by navState.navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val bottomNavDestination =
        BottomNavDestination.entries.find { it.route == currentRoute?.substringBefore("/") }
    var uiVisibility by remember { mutableStateOf(false) }
    var isSearchActivated by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    fun handleBackButton() {
        if (isSearchActivated) {
            isSearchActivated = false
        } else {
            if (bottomNavDestination == BottomNavDestination.Home) {
                onExitApp()
            } else {
                navState.navHostController.navigateToHomeNavGraph()
            }
        }
    }

    Scaffold(
        topBar = {
            MoniePointCustomAppBar(
                bottomNavDestination = bottomNavDestination,
                onTapBackIcon = {
                    handleBackButton()
                },
                title = bottomNavDestination?.label ?: "",
                backgroundColor = MaterialTheme.colorScheme.primary,
                isVisible = uiVisibility,
                isSearchActivated = isSearchActivated,
                onTapSearchField = {
                    isSearchActivated = true
                },
                onSearchQueryChange = { query ->
                    viewModel.sendEvent(
                        MainActivityEvent.OnInputSearchQuery(
                            query
                        )
                    )
                },
                searchQuery = uiState.searchQuery
            )
        },
        bottomBar = {
            AnimatedVisibility(
                uiVisibility && bottomNavDestination == BottomNavDestination.Home && !isSearchActivated,
                enter = slideInVertically(
                    initialOffsetY = { it },
                ) + fadeIn(),
                exit = fadeOut()
            ) {
                BottomNavigationBar(navState.navHostController)
            }
        }
    ) { padding ->

        AnimatedVisibility(
            uiVisibility && !isSearchActivated,
            enter = slideInVertically(
                initialOffsetY = { it },
            ) + fadeIn(),
            exit = fadeOut()
        ) {
            AppNavGraph(
                navState.navHostController,
                modifier = Modifier.padding(padding),
                onTapBackIcon = {
                    handleBackButton()
                }
            )
        }

        AnimatedVisibility(
            isSearchActivated,
            enter = slideInVertically(
                initialOffsetY = { it },
            ) + fadeIn(),
            exit = fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .padding(padding)
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp)
                ) {
                    itemsIndexed(uiState.shipments) { index: Int, shipment: ShipmentEntity ->
                        SearchItem(shipment = shipment)
                        if (uiState.shipments.lastIndex != index) {
                            HorizontalDivider(
                                thickness = 0.5.dp,
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        uiVisibility = true
    }
    LaunchedEffect(Unit) {
        viewModel.sendEvent(MainActivityEvent.OnLoadUiData)
    }
}

@Composable
@Preview
private fun MoniePointAppPreview() {
    MoniePointTheme {
        MoniePointApp(onExitApp = {})
    }
}