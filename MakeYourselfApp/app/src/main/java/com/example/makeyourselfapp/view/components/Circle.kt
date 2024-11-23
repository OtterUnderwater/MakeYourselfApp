package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun CircleGradient() {
    val colors = AppDesign.colors.gradient
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(
                Brush.linearGradient(
                    colors = colors
                ),
                radius = size.width / 2,
                alpha = 0.5f,
                center = center,
                style = Fill
            )
        }
    )
}

@Composable
fun PartGradient(start: Float, arc: Float, percent: Float) {
    val colors = AppDesign.colors.gradient
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            val innerSize = size.width * percent
            drawArc(
                brush = Brush.linearGradient(colors = colors),
                startAngle = start,
                sweepAngle = arc,
                useCenter = true,
                style = Fill,
                size = Size(innerSize, innerSize),
                topLeft = Offset((size.width - innerSize) / 2, (size.height - innerSize) / 2)
            )
        }
    )
}


