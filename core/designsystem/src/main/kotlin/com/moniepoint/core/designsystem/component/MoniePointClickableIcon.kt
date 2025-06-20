package com.moniepoint.core.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniepoint.core.designsystem.R
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun MoniePointClickableIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
    color: Color = Color.Unspecified,
    rippleColor: Color = MaterialTheme.colorScheme.primaryContainer,
    isEnabled: Boolean = true,
) {
    Icon(
        imageVector = icon,
        contentDescription = stringResource(R.string.desc_clickable_icon),
        tint = color,
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                enabled = isEnabled,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    color = rippleColor,
                ),
            )
            .padding(4.dp),
    )
}

@Composable
@Preview(showBackground = true)
private fun MoniePointClickableIconPreview1() {
    MoniePointTheme {
        MoniePointClickableIcon(
            modifier = Modifier
                .size(32.dp)
                .padding(4.dp),
            icon = MoniePointIcons.Notification,
            onClick = {},
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun MoniePointClickableIconPreview2() {
    MoniePointTheme {
        MoniePointClickableIcon(
            modifier = Modifier
                .size(32.dp)
                .padding(4.dp),
            icon = MoniePointIcons.ChevronLeft,
            onClick = {},
            color = MaterialTheme.colorScheme.primary,
        )
    }
}
