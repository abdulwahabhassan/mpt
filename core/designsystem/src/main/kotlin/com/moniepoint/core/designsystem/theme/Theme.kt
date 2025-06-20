package com.moniepoint.core.designsystem.theme

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun MoniePointTheme(
    content: @Composable () -> Unit,
) {
    val moniePointLightColorScheme = lightColorScheme(
        primary = purple06,
        onPrimary = DecorativeColor.white,
        primaryContainer = purple01,
        onPrimaryContainer = grey04,
        inversePrimary = purple03.copy(0.5f),

        secondary = pink04,
        onSecondary = purple05,

        tertiary = grey04,
        onTertiary = DecorativeColor.white,
        tertiaryContainer = grey02.copy(alpha = 0.5f),
        onTertiaryContainer = DecorativeColor.white,

        error = DecorativeColor.red02,
        onError = DecorativeColor.white,
        errorContainer = pink02,
        onErrorContainer = DecorativeColor.red02,

        background = grey00,
        surface = grey00,
        surfaceVariant = DecorativeColor.white,
        outlineVariant = grey01,
    )

    CompositionLocalProvider(LocalRippleTheme provides MoniePointRippleTheme) {
        MaterialTheme(
            colorScheme = moniePointLightColorScheme,
            typography = moniepointTypography,
            content = content,
        )
    }
}
