package com.example.makeyourselfapp.view.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.makeyourselfapp.R
import com.example.makeyourselfapp.models.theme.AppTypography

val GeologicaFontFamily = FontFamily(
    Font(R.font.geologica_bold, FontWeight.Bold),
    Font(R.font.geologica_regular, FontWeight.Medium),
    Font(R.font.geologica_light, FontWeight.Light)
)

val Typography = AppTypography(
    titleLarge = TextStyle(
        fontFamily = GeologicaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp),
    titleMedium = TextStyle(
        fontFamily = GeologicaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp),
    bodyBold = TextStyle(
        fontFamily = GeologicaFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp),
    bodyMedium = TextStyle(
        fontFamily = GeologicaFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp),
    bodyLight = TextStyle(
        fontFamily = GeologicaFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp)
)