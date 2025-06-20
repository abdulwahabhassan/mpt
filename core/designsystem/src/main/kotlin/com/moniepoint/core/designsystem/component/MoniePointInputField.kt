package com.moniepoint.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun MoniePointInputField(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (TextFieldValue) -> Unit,
    placeholderText: String = "",
    labelText: String = "",
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    feedbackText: String = "",
    isPasswordField: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: () -> Unit = { },
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    horizontalPadding: Dp = 24.dp,
    minLines: Int = 1,
    maxlines: Int = 1,
    onSurfaceAreaClick: (() -> Unit)? = null,
    isFieldMandatory: Boolean = true,
    showFeedBackText: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    var isVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = DecorativeColor.black,
        unfocusedTextColor = DecorativeColor.black,
        disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        errorTextColor = MaterialTheme.colorScheme.error,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        disabledContainerColor = containerColor,
        errorContainerColor = MaterialTheme.colorScheme.errorContainer,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
        disabledLeadingIconColor = MaterialTheme.colorScheme.tertiary,
        errorLeadingIconColor = MaterialTheme.colorScheme.error,
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        disabledTrailingIconColor = MaterialTheme.colorScheme.tertiary,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
        disabledLabelColor = MaterialTheme.colorScheme.tertiary,
        errorLabelColor = MaterialTheme.colorScheme.error,
        focusedSupportingTextColor = MaterialTheme.colorScheme.primary,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.primary,
        disabledSupportingTextColor = MaterialTheme.colorScheme.tertiary,
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
        focusedPrefixColor = MaterialTheme.colorScheme.primary,
        unfocusedPrefixColor = MaterialTheme.colorScheme.primary,
        errorPrefixColor = MaterialTheme.colorScheme.error,
        disabledPrefixColor = MaterialTheme.colorScheme.tertiary,
        focusedSuffixColor = MaterialTheme.colorScheme.primary,
        unfocusedSuffixColor = MaterialTheme.colorScheme.primary,
        disabledSuffixColor = MaterialTheme.colorScheme.tertiary,
        errorSuffixColor = MaterialTheme.colorScheme.error,
        focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        errorPlaceholderColor = MaterialTheme.colorScheme.error,
    )

    Box {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (labelText.isNotEmpty()) {
                Text(
                    text = buildAnnotatedString {
                        append(labelText)
                        if (isFieldMandatory) withStyle(
                            MaterialTheme.typography.titleSmall.copy(
                                color = DecorativeColor.red02
                            ).toSpanStyle()
                        ) {
                            append(" *")
                        }
                    },
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
            if (isPasswordField) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = if (labelText.isNotEmpty()) 8.dp else 0.dp)
                        .border(
                            width = 1.dp,
                            color = if (isFocused) {
                                if (!isEnabled) {
                                    MaterialTheme.colorScheme.tertiary
                                } else if (isError) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    Color.Transparent
                                }
                            } else {
                                Color.Transparent
                            },
                            shape = MaterialTheme.shapes.medium,
                        )
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    value = textFieldValue,
                    onValueChange = onTextFieldValueChange,
                    enabled = isEnabled,
                    readOnly = readOnly,
                    isError = isError,
                    singleLine = maxlines == 1,
                    minLines = minLines,
                    maxLines = maxlines,
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = textStyle,
                        )
                    },
                    keyboardOptions = keyboardOptions,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    shape = MaterialTheme.shapes.medium,
                    visualTransformation = if (isVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation('*')
                    },
                    leadingIcon = if (leadingIcon != null) {
                        {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = leadingIcon,
                                    contentDescription = "Leading icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .clickable(
                                            onClick = onTrailingIconClick,
                                            enabled = isEnabled,
                                            role = Role.Button,
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(
                                                bounded = true,
                                                color = if (isError) {
                                                    MaterialTheme.colorScheme.error
                                                } else {
                                                    MaterialTheme.colorScheme.primaryContainer
                                                },
                                            ),
                                        ),
                                    tint = MaterialTheme.colorScheme.tertiary,
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                VerticalDivider(
                                    Modifier
                                        .height(24.dp)
                                        .padding(start = 4.dp),
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    } else null,
                    trailingIcon = {
                        Icon(
                            imageVector = if (isVisible) {
                                MoniePointIcons.VisibilityOff
                            } else {
                                MoniePointIcons.VisibilityOn
                            },
                            contentDescription = "Visibility Icon",
                            modifier = Modifier
                                .size(24.dp)
                                .clip(MaterialTheme.shapes.small)
                                .clickable(
                                    onClick = {
                                        isVisible = !isVisible
                                    },
                                    enabled = isEnabled,
                                    role = Role.Button,
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        bounded = true,
                                        color = if (isError) {
                                            MaterialTheme.colorScheme.error
                                        } else {
                                            MaterialTheme.colorScheme.primaryContainer
                                        },),
                                ),
                            tint = if (isError) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.primary
                            },
                        )
                    },
                    colors = textFieldColors,
                )
            } else {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = if (labelText.isNotEmpty()) 8.dp else 0.dp)
                        .border(
                            width = 1.dp,
                            color = if (isFocused) {
                                if (!isEnabled) {
                                    MaterialTheme.colorScheme.tertiary
                                } else if (isError) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    Color.Transparent
                                }
                            } else {
                                Color.Transparent
                            },
                            shape = MaterialTheme.shapes.medium,
                        )
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    value = textFieldValue,
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = textStyle,
                        )
                    },
                    visualTransformation = visualTransformation,
                    onValueChange = onTextFieldValueChange,
                    enabled = isEnabled,
                    readOnly = readOnly,
                    isError = isError,
                    singleLine = maxlines == 1,
                    minLines = minLines,
                    maxLines = maxlines,
                    keyboardOptions = keyboardOptions,
                    textStyle = textStyle,
                    shape = MaterialTheme.shapes.medium,
                    colors = textFieldColors,
                    leadingIcon = if (leadingIcon != null) {
                        {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = leadingIcon,
                                    contentDescription = "Leading icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .clickable(
                                            onClick = onTrailingIconClick,
                                            enabled = isEnabled,
                                            role = Role.Button,
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(
                                                bounded = true,
                                                color = if (isError) {
                                                    MaterialTheme.colorScheme.error
                                                } else {
                                                    MaterialTheme.colorScheme.primaryContainer
                                                },
                                            ),
                                        ),
                                    tint = MaterialTheme.colorScheme.tertiary,
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                VerticalDivider(
                                    Modifier
                                        .height(24.dp),
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    } else null,
                    trailingIcon = if (trailingIcon != null) {
                        {
                            Icon(
                                imageVector = trailingIcon,
                                contentDescription = "Trailing icon",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(MaterialTheme.shapes.small)
                                    .clickable(
                                        onClick = onTrailingIconClick,
                                        enabled = isEnabled,
                                        role = Role.Button,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            bounded = true,
                                            color = if (isError) {
                                                MaterialTheme.colorScheme.error
                                            } else {
                                                MaterialTheme.colorScheme.primaryContainer
                                            },
                                        ),),
                                tint = Color.Unspecified,
                            )
                        }
                    } else null,
                )
            }
            if (showFeedBackText) {
                Text(
                    text = feedbackText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (!isEnabled) {
                            MaterialTheme.colorScheme.tertiary
                        } else if (isError) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
        }
        if (onSurfaceAreaClick != null) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = onSurfaceAreaClick, enabled = isEnabled),
            )

        }
    }
}

@Composable
fun MoniePointInputField2(
    textFieldValue: TextFieldValue,
    onTextFieldValueChange: (TextFieldValue) -> Unit,
    placeholderText: String = "",
    labelText: String = "",
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    feedbackText: String = "",
    isPasswordField: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: Painter? = null,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: () -> Unit = { },
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    horizontalPadding: Dp = 24.dp,
    minLines: Int = 1,
    maxlines: Int = 1,
    onSurfaceAreaClick: (() -> Unit)? = null,
    isFieldMandatory: Boolean = true,
    showFeedBackText: Boolean = false,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer
) {
    var isVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.tertiary,
        unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
        disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
        errorTextColor = MaterialTheme.colorScheme.error,
        focusedContainerColor = containerColor,
        unfocusedContainerColor = containerColor,
        disabledContainerColor = containerColor,
        errorContainerColor = MaterialTheme.colorScheme.errorContainer,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
        disabledLeadingIconColor = MaterialTheme.colorScheme.tertiary,
        errorLeadingIconColor = MaterialTheme.colorScheme.error,
        focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
        disabledTrailingIconColor = MaterialTheme.colorScheme.tertiary,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
        disabledLabelColor = MaterialTheme.colorScheme.tertiary,
        errorLabelColor = MaterialTheme.colorScheme.error,
        focusedSupportingTextColor = MaterialTheme.colorScheme.primary,
        unfocusedSupportingTextColor = MaterialTheme.colorScheme.primary,
        disabledSupportingTextColor = MaterialTheme.colorScheme.tertiary,
        errorSupportingTextColor = MaterialTheme.colorScheme.error,
        focusedPrefixColor = MaterialTheme.colorScheme.primary,
        unfocusedPrefixColor = MaterialTheme.colorScheme.primary,
        errorPrefixColor = MaterialTheme.colorScheme.error,
        disabledPrefixColor = MaterialTheme.colorScheme.tertiary,
        focusedSuffixColor = MaterialTheme.colorScheme.primary,
        unfocusedSuffixColor = MaterialTheme.colorScheme.primary,
        disabledSuffixColor = MaterialTheme.colorScheme.tertiary,
        errorSuffixColor = MaterialTheme.colorScheme.error,
        focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        disabledPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
        errorPlaceholderColor = MaterialTheme.colorScheme.error,
    )

    Box {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (labelText.isNotEmpty()) {
                Text(
                    text = buildAnnotatedString {
                        append(labelText)
                        if (isFieldMandatory) withStyle(
                            MaterialTheme.typography.titleSmall.copy(
                                color = DecorativeColor.red02
                            ).toSpanStyle()
                        ) {
                            append(" *")
                        }
                    },
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
            if (isPasswordField) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = if (labelText.isNotEmpty()) 8.dp else 0.dp)
                        .border(
                            width = 1.dp,
                            color = if (isFocused) {
                                if (!isEnabled) {
                                    MaterialTheme.colorScheme.tertiary
                                } else if (isError) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    Color.Transparent
                                }
                            } else {
                                Color.Transparent
                            },
                            shape = MaterialTheme.shapes.medium,
                        )
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    value = textFieldValue,
                    onValueChange = onTextFieldValueChange,
                    enabled = isEnabled,
                    readOnly = readOnly,
                    isError = isError,
                    singleLine = maxlines == 1,
                    minLines = minLines,
                    maxLines = maxlines,
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = textStyle,
                        )
                    },
                    keyboardOptions = keyboardOptions,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    shape = MaterialTheme.shapes.medium,
                    visualTransformation = if (isVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation('*')
                    },
                    leadingIcon = if (leadingIcon != null) {
                        {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = leadingIcon,
                                    contentDescription = "Leading icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .clickable(
                                            onClick = onTrailingIconClick,
                                            enabled = isEnabled,
                                            role = Role.Button,
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(
                                                bounded = true,
                                                color = if (isError) {
                                                    MaterialTheme.colorScheme.error
                                                } else {
                                                    MaterialTheme.colorScheme.primaryContainer
                                                },
                                            ),
                                        ),
                                    tint = Color.Unspecified,
                                )
                                VerticalDivider(
                                    Modifier
                                        .height(24.dp)
                                        .padding(start = 2.dp),
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    } else null,
                    trailingIcon = {
                        Icon(
                            imageVector = if (isVisible) {
                                MoniePointIcons.VisibilityOff
                            } else {
                                MoniePointIcons.VisibilityOn
                            },
                            contentDescription = "Visibility Icon",
                            modifier = Modifier
                                .size(24.dp)
                                .clip(MaterialTheme.shapes.small)
                                .clickable(
                                    onClick = {
                                        isVisible = !isVisible
                                    },
                                    enabled = isEnabled,
                                    role = Role.Button,
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple(
                                        bounded = true,
                                        color = if (isError) {
                                            MaterialTheme.colorScheme.error
                                        } else {
                                            MaterialTheme.colorScheme.primaryContainer
                                        },),
                                ),
                            tint = if (isError) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.primary
                            },
                        )
                    },
                    colors = textFieldColors,
                )
            } else {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = horizontalPadding, vertical = if (labelText.isNotEmpty()) 8.dp else 0.dp)
                        .border(
                            width = 1.dp,
                            color = if (isFocused) {
                                if (!isEnabled) {
                                    MaterialTheme.colorScheme.tertiary
                                } else if (isError) {
                                    MaterialTheme.colorScheme.error
                                } else {
                                    Color.Transparent
                                }
                            } else {
                                Color.Transparent
                            },
                            shape = MaterialTheme.shapes.medium,
                        )
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    value = textFieldValue,
                    placeholder = {
                        Text(
                            text = placeholderText,
                            style = textStyle,
                        )
                    },
                    visualTransformation = visualTransformation,
                    onValueChange = onTextFieldValueChange,
                    enabled = isEnabled,
                    readOnly = readOnly,
                    isError = isError,
                    singleLine = maxlines == 1,
                    minLines = minLines,
                    maxLines = maxlines,
                    keyboardOptions = keyboardOptions,
                    textStyle = textStyle,
                    shape = MaterialTheme.shapes.medium,
                    colors = textFieldColors,
                    leadingIcon = if (leadingIcon != null) {
                        {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = leadingIcon,
                                    contentDescription = "Leading icon",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .clickable(
                                            onClick = onTrailingIconClick,
                                            enabled = isEnabled,
                                            role = Role.Button,
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple(
                                                bounded = true,
                                                color = if (isError) {
                                                    MaterialTheme.colorScheme.error
                                                } else {
                                                    MaterialTheme.colorScheme.primaryContainer
                                                },
                                            ),
                                        ),
                                    tint = Color.Unspecified,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                VerticalDivider(
                                    Modifier
                                        .height(24.dp)
                                        .padding(start = 2.dp),
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    } else null,
                    trailingIcon = if (trailingIcon != null) {
                        {
                            Icon(
                                imageVector = trailingIcon,
                                contentDescription = "Trailing icon",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(MaterialTheme.shapes.small)
                                    .clickable(
                                        onClick = onTrailingIconClick,
                                        enabled = isEnabled,
                                        role = Role.Button,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(
                                            bounded = true,
                                            color = if (isError) {
                                                MaterialTheme.colorScheme.error
                                            } else {
                                                MaterialTheme.colorScheme.primaryContainer
                                            },
                                        ),),
                                tint = Color.Unspecified,
                            )
                        }
                    } else null,
                )
            }
            if (showFeedBackText) {
                Text(
                    text = feedbackText,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = if (!isEnabled) {
                            MaterialTheme.colorScheme.tertiary
                        } else if (isError) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = horizontalPadding),
                )
            }
        }
        if (onSurfaceAreaClick != null) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .alpha(0f)
                    .clickable(onClick = onSurfaceAreaClick, enabled = isEnabled),
            )

        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointInputFieldPreview1() {
    MoniePointTheme {
        MoniePointInputField(
            textFieldValue = TextFieldValue(text = ""),
            onTextFieldValueChange = {},
            labelText = "Phone Number",
            placeholderText = "e.g 08167039661",
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointInputFieldPreview2() {
    MoniePointTheme {
        Column {
            MoniePointInputField(
                textFieldValue = TextFieldValue(text = ""),
                onTextFieldValueChange = {},
                labelText = "Passcode",
                placeholderText = "Input password",
                isPasswordField = true,
                isError = true,
                feedbackText = "Invalid input",
            )
            Spacer(modifier = Modifier.height(16.dp))
            MoniePointInputField(
                textFieldValue = TextFieldValue(text = ""),
                onTextFieldValueChange = {},
                labelText = "Home address",
                placeholderText = "Enter your home address",
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointInputFieldPreview3() {
    MoniePointTheme {
        MoniePointInputField(
            textFieldValue = TextFieldValue(text = ""),
            onTextFieldValueChange = {},
            labelText = "Home address",
            placeholderText = "Enter your home address",
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointInputFieldPreview4() {
    MoniePointTheme {
        MoniePointInputField(
            textFieldValue = TextFieldValue(text = "Hassan Abdulwahab"),
            onTextFieldValueChange = {},
            labelText = "Name",
            placeholderText = "What's your name?",
            isEnabled = false,
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointInputFieldPreview5() {
    MoniePointTheme {
        MoniePointInputField(
            textFieldValue = TextFieldValue(text = ""),
            onTextFieldValueChange = {},
            placeholderText = "What's your name?",
            isEnabled = false,
            leadingIcon = MoniePointIcons.Calculate
        )
    }
}
