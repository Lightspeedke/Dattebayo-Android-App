package com.jeric.narutobook.ui.presentation.character.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jeric.narutobook.ui.theme.Green300
import com.jeric.narutobook.ui.theme.Yellow400

@Composable
fun CharacterStatsItem(
    jutsu: String,
    modifier: Modifier = Modifier
) {
    val animationProgress = remember {
        Animatable(initialValue = 0f)
    }

    val percentage = remember {
        calculatePercentage(if(jutsu.length > 15) 20 else jutsu.length, 30)
    }

    val natureType = remember {
        jutsu.split(" ").first()
    }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = (8 * percentage).toInt(),
                easing = LinearEasing
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = natureType,
            color = MaterialTheme.colorScheme.onBackground.copy(.8f),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(.3f)
        )

        Text(
            text = "${percentage.toInt()}%",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.weight(.2f)
        )

        val progress = percentage / 100f
        val animatedProgress = progress * animationProgress.value

        val progressColor = if (progress >= 0.5f) Green300 else Yellow400
        val progressTrackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)

        Box(
            modifier = Modifier
                .weight(.5f)
                .height(10.dp)
                .drawBehind {
                    drawRoundRect(
                        color = progressTrackColor,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(size.height, size.height),
                    )

                    drawRoundRect(
                        color = progressColor,
                        topLeft = Offset.Zero,
                        size = Size(width = (size.width * animatedProgress).toFloat(), height = size.height),
                        cornerRadius = CornerRadius(size.height, size.height),
                    )
                }
        )
    }
}

fun calculatePercentage(part: Int, total: Int): Double {
    return if (total != 0) (part.toDouble() / total) * 100 else 0.0
}
