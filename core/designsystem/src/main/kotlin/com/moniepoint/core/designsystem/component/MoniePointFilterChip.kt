package com.moniepoint.core.designsystem.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun MoniePointFilterChip(
    title: String,
    onClick: (Boolean) -> Unit,
    isSelected: Boolean,
    leadingIcon: ImageVector,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
) {

    val animatedScale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        animationSpec = tween()
    )

    MoniePointClickableIconText(
        modifier = Modifier
            .scale(animatedScale)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.tertiary,
                shape = MaterialTheme.shapes.small
            ),
        text = buildAnnotatedString {
            withStyle(
                MaterialTheme.typography.bodySmall.copy(color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary)
                    .toSpanStyle()
            ) { append(title) }
        },
        onClick = {
            onClick(isSelected)
        },
        backgroundColor = if (isSelected) DecorativeColor.deepish else Color.Transparent,
        backgroundShape = MaterialTheme.shapes.small,
        leadingIcon = {
            if (isSelected) Icon(
                leadingIcon,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = null
            )
        },
        textStyle = textStyle,
        contentPaddingValues = PaddingValues(horizontal = 6.dp)
    )
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointFilterChipPreview() {
    MoniePointTheme {
        MoniePointFilterChip(
            title = "Credit",
            onClick = {},
            isSelected = false,
            leadingIcon = MoniePointIcons.Check
        )
    }
}