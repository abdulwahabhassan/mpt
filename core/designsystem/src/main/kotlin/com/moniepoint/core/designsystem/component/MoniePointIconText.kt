package com.moniepoint.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moniepoint.core.designsystem.icon.MoniePointIcons
import com.moniepoint.core.designsystem.theme.MoniePointTheme

@Composable
fun MoniePointIconText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    backgroundColor: Color = Color.Transparent,
    backgroundShape: Shape = CircleShape,
    leadingIcon: @Composable () -> Unit = { },
    trailingIcon: @Composable () -> Unit = { },
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
    textAlign: TextAlign = TextAlign.Center,
    textPadding: PaddingValues = PaddingValues(vertical = 4.dp, horizontal = 4.dp),
    contentPaddingValues: PaddingValues = PaddingValues(vertical = 8.dp, horizontal = 10.dp),
    maxLines: Int = Int.MAX_VALUE
) {
    Row(
        modifier = modifier
            .background(
                shape = backgroundShape,
                color = backgroundColor,
            )
            .clip(backgroundShape)
            .padding(contentPaddingValues),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        leadingIcon()
        if (text.text.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(textPadding),
                text = text,
                style = textStyle,
                textAlign = textAlign,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis
            )
        }
        trailingIcon()
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointClickableIconTextPreview1() {
    MoniePointTheme {
        MoniePointClickableIconText(
            text = buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.bodyMedium.toSpanStyle(),
                ) {
                    append("Had coffee yet? ")
                }
                withStyle(
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                    ).toSpanStyle(),
                ) {
                    append("Order a cup!")
                }
            },
            onClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointClickableIconTextPreview2() {
    MoniePointTheme {
        MoniePointClickableIconText(
            text = buildAnnotatedString {
                append("Click Me")
            },
            onClick = {},
            trailingIcon = {
                Icon(imageVector = MoniePointIcons.ChevronUp, contentDescription = null)
            },
            leadingIcon = {
                Icon(imageVector = MoniePointIcons.ChevronUp, contentDescription = null)
            }
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
private fun MoniePointClickableIconTextPreview3() {
    MoniePointTheme {
        MoniePointClickableIconText(
            text = buildAnnotatedString {
                append("Click Me")
            },
            onClick = {},
            trailingIcon = {
                Icon(imageVector = MoniePointIcons.ChevronUp, contentDescription = null)
            },
            isEnabled = true
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, apiLevel = 34)
private fun MoniePointIconTextPreview4() {
    MoniePointTheme {
        MoniePointIconText(
            text = buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.bodyMedium.toSpanStyle(),
                ) {
                    append("AbdulwahabHassan HassanAbdulwahabkbskjb sbjkjsbkjbsj")
                }
            },
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            backgroundShape = MaterialTheme.shapes.small,
            textAlign = TextAlign.End,)
    }
}
