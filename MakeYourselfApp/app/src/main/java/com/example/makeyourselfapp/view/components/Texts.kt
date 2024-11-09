package com.example.makeyourselfapp.view.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun TextTittlePrimary(text: String){
    Text(
        text = text,
        style = AppDesign.typography.titleLarge,
        color = AppDesign.colors.primary
    )
}

@Composable
fun TextTittle(text: String){
    Text(
        text = text,
        style = AppDesign.typography.titleMedium,
        color = AppDesign.colors.textColor
    )
}

@Composable
fun TextClickable(text: String, onClick: () -> Unit){
    Text(
        text = text,
        style = AppDesign.typography.titleMedium,
        color = AppDesign.colors.primary,
        modifier = Modifier.clickable(onClick = { onClick() })
    )
}