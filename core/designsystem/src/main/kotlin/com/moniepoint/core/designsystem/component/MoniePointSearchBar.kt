package com.moniepoint.core.designsystem.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoniePointSearchBar(
    modifier: Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    searchPlaceholder: String,
    trailingIcon: (@Composable () -> Unit)? = null,
    isEnabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    placeholderTextColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onTap: () -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            if (interaction is PressInteraction.Press) {
                onTap()
            }
        }
    }


    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .padding(start = 16.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        bottomStart = 8.dp,
                    ),
                ),

            ) {
            Icon(
                modifier = modifier
                    .size(20.dp)
                    .align(Alignment.Center)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp,
                        ),
                    ),
                imageVector = MoniePointIcons.Search,
                contentDescription = "Search icon",
                tint = contentColor,
            )
        }
        TextField(
            modifier = modifier
                .weight(1f)
                .focusRequester(FocusRequester()),
            value = query,
            onValueChange = onQueryChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = searchPlaceholder,
                    style = MaterialTheme.typography.bodyMedium.copy(color = placeholderTextColor),
                )
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = contentColor,
                unfocusedTextColor = contentColor,
                cursorColor = contentColor,
            ),
            textStyle = MaterialTheme.typography.bodyMedium,
            enabled = isEnabled,
            interactionSource = interactionSource,
        )
        trailingIcon?.invoke()
    }
}

@Composable
@Preview()
private fun SearchBarPreview() {
    MoniePointTheme {
        MoniePointSearchBar(
            modifier = Modifier,
            query = "",
            onQueryChange = {},
            searchPlaceholder = "Enter the receipt number...",
            trailingIcon = {
                MoniePointClickableIcon(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(color = DecorativeColor.orange02, shape = CircleShape)
                        .size(40.dp)
                        .rotate(90f)
                        .padding(4.dp),
                    icon = MoniePointIcons.Flip,
                    color = MaterialTheme.colorScheme.onPrimary,
                    onClick = { /*TODO*/ }
                )
            },
            isEnabled = true,
            onTap = {},
        )
    }
}
