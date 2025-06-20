package com.devhassan.moniepoint.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devhassan.moniepoint.app.bottomnav.BottomNavDestination
import com.moniepoint.core.AnimationConstant
import com.moniepoint.core.designsystem.R
import com.moniepoint.core.designsystem.component.MoniePointClickableIcon
import com.moniepoint.core.designsystem.component.MoniePointSearchBar
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun MoniePointCustomAppBar(
    bottomNavDestination: BottomNavDestination?,
    onTapBackIcon: (() -> Unit)? = null,
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    isVisible: Boolean = false,
    isSearchActivated: Boolean = false,
    onTapSearchField: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    AnimatedVisibility(
        isVisible,
        enter = slideInVertically(
            initialOffsetY = { -it },
        ),
        exit = slideOutVertically(
            targetOffsetY = { -it },
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            when (bottomNavDestination) {
                null -> {}

                BottomNavDestination.Home -> Column {
                    AnimatedVisibility(
                        !isSearchActivated,
                        enter = expandVertically(
                        ) + fadeIn(),
                        exit = shrinkVertically(
                        ) + fadeOut()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Image(
                                    painterResource(R.drawable.profile_pic),
                                    null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(shape = CircleShape),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                                Column {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            MoniePointIcons.Location,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            "Your location",
                                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
                                        )
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            "Wertheimer, lllinois",
                                            style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary)
                                        )
                                        Icon(
                                            MoniePointIcons.ChevronDown,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onPrimary,

                                            )
                                    }
                                }
                            }
                            MoniePointClickableIcon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        shape = CircleShape,
                                    )
                                    .padding(2.dp),
                                icon = MoniePointIcons.Notification,
                                onClick = {}
                            )
                        }

                    }
                    AnimatedVisibility(
                        !isSearchActivated,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        val focusManager = LocalFocusManager.current
                        AnimatedVisibility(
                            isSearchActivated,
                            enter = expandHorizontally() + fadeIn(),
                            exit = shrinkHorizontally(
                            ) + fadeOut(
                            )
                        ) {
                            MoniePointClickableIcon(
                                modifier = Modifier
                                    .size(28.dp),
                                icon = MoniePointIcons.Back,
                                onClick = {
                                    if (onTapBackIcon != null) {
                                        focusManager.clearFocus()
                                        onTapBackIcon()

                                    }
                                },
                                color = MaterialTheme.colorScheme.onPrimary,
                                rippleColor = MaterialTheme.colorScheme.primary
                            )
                        }
                        val animatedSearchFieldHeight by animateDpAsState(
                            targetValue = if (isSearchActivated) 56.dp else 48.dp,
                            animationSpec = tween(durationMillis = AnimationConstant.FIVE_HUNDRED)
                        )
                        MoniePointSearchBar(
                            modifier = Modifier.height(animatedSearchFieldHeight),
                            onTap = {
                                if (!isSearchActivated) {
                                    onTapSearchField()
                                }
                            },
                            query = searchQuery,
                            onQueryChange = onSearchQueryChange,
                            searchPlaceholder = "Enter the receipt number...",
                            trailingIcon = {
                                val animatedIconPadding by animateDpAsState(
                                    targetValue = if (isSearchActivated) 8.dp else 4.dp,
                                    animationSpec = tween(durationMillis = AnimationConstant.FIVE_HUNDRED)
                                )

                                MoniePointClickableIcon(
                                    modifier = Modifier
                                        .padding(animatedIconPadding)
                                        .background(
                                            color = DecorativeColor.orange02,
                                            shape = CircleShape
                                        )
                                        .size(40.dp)
                                        .rotate(90f)
                                        .padding(4.dp),
                                    icon = MoniePointIcons.Flip,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    onClick = { }
                                )
                            },
                        )

                    }
                    Spacer(
                        modifier = Modifier
                            .height(24.dp)
                    )
                }

                BottomNavDestination.Calculate,
                BottomNavDestination.Shipment,
                BottomNavDestination.Profile -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (onTapBackIcon != null) {
                                MoniePointClickableIcon(
                                    modifier = Modifier
                                        .size(28.dp),
                                    icon = MoniePointIcons.Back,
                                    onClick = onTapBackIcon,
                                    rippleColor = Color.Transparent,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                            Text(
                                title,
                                modifier = Modifier.weight(1f),
                                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                                textAlign = TextAlign.Center
                            )
                            if (onTapBackIcon != null) {
                                Box(modifier = Modifier.size(28.dp))
                            }

                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun MoniePointCustomAppBarPreview() {
    MoniePointTheme {
        MoniePointCustomAppBar(
            BottomNavDestination.Calculate,
            onTapBackIcon = { },
            title = "",
            backgroundColor = MaterialTheme.colorScheme.primary,
            onTapSearchField = {},
            onSearchQueryChange = {},
            isSearchActivated = true,
            isVisible = true,
            searchQuery = ""
        )
    }
}
