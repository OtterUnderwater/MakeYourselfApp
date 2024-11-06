package com.example.makeyourselfapp.view.screens.topbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.models.theme.CurrentTheme
import com.example.makeyourselfapp.view.components.TextTittle
import com.example.makeyourselfapp.view.ui.theme.AppDesign
import com.example.makeyourselfapp.view.ui.theme.ListColorTheme

@Composable
fun TopBarCustom(controller: NavHostController, theme: MutableState<CurrentTheme>){
    Box(
        modifier = Modifier.height(90.dp).fillMaxWidth()
            .background(AppDesign.colors.background).padding(bottom = 10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceAround)
        {
            val gradient = Brush.verticalGradient(AppDesign.colors.gradient)
            IconButton(onClick = {})
            {
                Box(Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                    Canvas(Modifier.size(24.dp)) {
                        drawRoundRect(
                            topLeft = Offset(0f, 0f),
                            size = size,
                            cornerRadius = CornerRadius(12f, 12f),
                            brush = gradient
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.icon_output),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp).padding(start = 2.dp)
                    )
                }
            }
            TextTittle("Make Yourself")
            IconButton(
                onClick = {
                    val nextIndex = (theme.value.index + 1) % 5
                    theme.value = CurrentTheme(nextIndex, ListColorTheme[nextIndex])
                })
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.icon_moon),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp).graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(gradient, blendMode = BlendMode.SrcAtop)
                            }
                        }
                )
            }
        }
    }
}
