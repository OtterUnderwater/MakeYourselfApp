package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun CircleGradient() {
    var colors = AppDesign.colors.gradient
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
