package com.example.makeyourselfapp.view.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.makeyourselfapp.models.theme.AppTypography
import com.example.makeyourselfapp.models.theme.ColorTheme

//Ключ содержит тему по умолчанию
val LocalAppColorScheme = staticCompositionLocalOf { ListColorTheme[0] }
val LocalTypography = staticCompositionLocalOf { Typography }

//Функция для запуска и замены темы
@Composable
fun MakeYourselfAppTheme(
    currentTheme: ColorTheme = ListColorTheme[0],
    typography: AppTypography = LocalTypography.current,
    content: @Composable () -> Unit
){
    val rippleIndication = rememberRipple()
    CompositionLocalProvider(
        LocalAppColorScheme provides currentTheme,
        LocalTypography provides typography,
        LocalIndication provides rippleIndication,
        content = content
    )
}

//Объект для обращения к темам
object AppDesign {
    val colors: ColorTheme
        @Composable
        get() = LocalAppColorScheme.current
    val typography: AppTypography
        @Composable
        get() = LocalTypography.current
}
