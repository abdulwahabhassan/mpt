package com.moniepoint.feature.estimate.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp
import com.moniepoint.core.designsystem.theme.DecorativeColor
import com.moniepoint.core.ui.util.formatAmount

@Composable
fun AnimatedCountingNumber(
    targetValue: Double = 0.00,
    durationMillis: Int = 2000

) {
    val animatedValue = remember { Animatable(0f) }

    LaunchedEffect(targetValue) {
        animatedValue.animateTo(
            targetValue.toFloat(),
            animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
        )
    }
    val displayValue = formatAmount(animatedValue.value)
    Text(
        "$$displayValue",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = DecorativeColor.green02,
            fontSize = 22.sp
        ),

        )
}