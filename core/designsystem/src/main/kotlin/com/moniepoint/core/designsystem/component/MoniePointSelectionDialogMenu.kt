package com.moniepoint.core.designsystem.component

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> MoniePointSelectionDialogMenu(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    label: String,
    showLabel: Boolean = true,
    feedBack: String,
    placeholder: String,
    items: List<T>,
    selectedIndex: Int = -1,
    onItemSelected: (index: Int, item: T) -> Unit,
    selectedItemToString: (T) -> String = { it.toString() },
    drawItem: @Composable (T, Boolean, Boolean, () -> Unit) -> Unit = { item, selected, itemEnabled, onClick ->
        MoniePointSelectionDialogMenuItem(
            text = item.toString(),
            selected = selected,
            enabled = itemEnabled,
            onClick = onClick,
        )
    },
    isFieldMandatory: Boolean = true,
    leadingIcon: Painter? = null,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier.height(IntrinsicSize.Min)) {
        MoniePointInputField2(
            textFieldValue = TextFieldValue(
                text = items.getOrNull(selectedIndex)?.let { selectedItemToString(it) } ?: "",
            ),
            onTextFieldValueChange = {
            },
            isEnabled = enabled,
            isError = isError,
            feedbackText = feedBack,
            readOnly = true,
            leadingIcon = leadingIcon,
            trailingIcon = if (expanded) MoniePointIcons.ChevronUp else MoniePointIcons.ChevronDown,
            labelText = if (showLabel) label else "",
            placeholderText = placeholder,
            onSurfaceAreaClick = {
                expanded = true
            },
            isFieldMandatory = isFieldMandatory,
            horizontalPadding = 0.dp,
            textStyle = MaterialTheme.typography.labelMedium.copy(color = DecorativeColor.black),
            containerColor = containerColor
        )
    }
    if (expanded) {
        Dialog(
            onDismissRequest = { expanded = false },
        ) {
            MoniePointTheme {
                Box(Modifier.padding(vertical = 80.dp)) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                    ) {
                        val listState = rememberLazyListState()
                        if (selectedIndex > -1) {
                            LaunchedEffect("ScrollToSelected") {
                                listState.scrollToItem(index = selectedIndex)
                            }
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth(),
                            state = listState
                        ) {
                            stickyHeader {
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.labelLarge,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .padding(top = 8.dp),)
                                }
                            }
                            itemsIndexed(items) { index, item ->
                                val selectedItem = index == selectedIndex
                                drawItem(
                                    item,
                                    selectedItem,
                                    true,
                                ) {
                                    onItemSelected(index, item)
                                    expanded = false
                                }
                                if (index < items.lastIndex) {
                                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MoniePointSelectionDialogMenuItem(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val contentColor = when {
        !enabled -> MaterialTheme.colorScheme.inversePrimary
        selected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.tertiary
    }
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        Box(
            modifier = Modifier
                .clickable(enabled) { onClick() }
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun MoniePointSelectionDialogMenuPreview() {
    MoniePointTheme {
        MoniePointSelectionDialogMenu(
            label = "Select",
            placeholder = "",
            items = listOf("Cap"),
            feedBack = "",
            onItemSelected = { _, _ -> },
            isFieldMandatory = true,
            leadingIcon = painterResource(MoniePointIcons.Package)
        )
    }
}