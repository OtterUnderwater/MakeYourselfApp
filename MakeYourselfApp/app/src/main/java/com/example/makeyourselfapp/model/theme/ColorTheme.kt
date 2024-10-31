package com.example.makeyourselfapp.model.theme

import androidx.compose.ui.graphics.Color

data class ColorTheme(
    val background: Color,
    val lightBackground: Color,
    val additional: Color,
    val textColor: Color,
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val accent: Color,
    val gradient: List<Color>
)