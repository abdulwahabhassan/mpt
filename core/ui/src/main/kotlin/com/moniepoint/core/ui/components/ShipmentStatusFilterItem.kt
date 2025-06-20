package com.moniepoint.core.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniepoint.core.AnimationConstant
import com.moniepoint.core.designsystem.component.MoniePointIconText
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme
import com.moniepoint.core.model.enums.ShipmentStatus

@Composable
fun ShipmentStatusFilterItem(
    filter: ShipmentStatus?,
    count: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(),
        label = "PressScale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .clip(CircleShape)
                .scale(scale)
                .clickable(
                    onClick = onClick,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication = null,
                )
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                filter?.filterTitle ?: "All",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = if (selected) 1f else 0.6f)
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            MoniePointIconText(
                text = buildAnnotatedString {
                    withStyle(
                        MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = if (selected) 1f else 0.6f)
                        ).toSpanStyle()
                    ) {
                        append(count.toString())
                    }
                },
                backgroundShape = MaterialTheme.shapes.medium,
                backgroundColor = if (selected) DecorativeColor.orange02 else MaterialTheme.colorScheme.onPrimary.copy(
                    alpha = 0.15f
                ),
                contentPaddingValues = PaddingValues(horizontal = 8.dp),
                textPadding = PaddingValues(vertical = 2.dp)
            )
        }
        Box(
            modifier = Modifier
                .height(2.dp)
                .background(color = if (selected) DecorativeColor.orange02 else Color.Transparent),
        )
    }
}


@Composable
@Preview()
private fun ShipmentStatusFilterItemPreview(
) {
    MoniePointTheme {
        ShipmentStatusFilterItem(ShipmentStatus.Completed, 3, selected = true, onClick = {})
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF543A9D)
private fun ShipmentStatusFilterItemPreview2(
) {
    MoniePointTheme {
        LazyRow {
            items(listOf(null).plus(ShipmentStatus.entries.toList())) { filter ->
                ShipmentStatusFilterItem(filter, 3, selected = filter?.ordinal == 2, onClick = {})
            }
        }
    }
}