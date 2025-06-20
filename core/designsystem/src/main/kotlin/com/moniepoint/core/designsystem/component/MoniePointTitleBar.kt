package com.moniepoint.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun MoniePointTitleBar(
    onTapBackIcon: (() -> Unit)? = null,
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (onTapBackIcon != null) {
                MoniePointClickableIcon(
                    modifier = Modifier
                        .size(28.dp),
                    icon = MoniePointIcons.Back,
                    onClick = onTapBackIcon,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                )
            }

            Text(
                title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .size(32.dp)
            )
        }
        if (content != null) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointTitleBarPreview() {
    MoniePointTheme {
        MoniePointTitleBar(
            onTapBackIcon = {},
            title = "Shipment history",
            content = {}
        )
    }
}
