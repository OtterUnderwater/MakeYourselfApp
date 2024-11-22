package com.example.makeyourselfapp.view.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.makeyourselfapp.view.ui.theme.AppDesign

@Composable
fun CheckBoxMenu(value: Boolean, color: Color, input: (Boolean) -> Unit)
{
    Checkbox(
        checked = value,
        onCheckedChange = { input(it) },
        colors = CheckboxDefaults.colors(
            checkedColor = color,
            checkmarkColor = AppDesign.colors.textColor
        )
    )
}

