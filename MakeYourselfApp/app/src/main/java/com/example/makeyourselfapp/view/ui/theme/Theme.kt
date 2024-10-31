package com.example.makeyourselfapp.view.ui.theme

import com.example.makeyourselfapp.models.theme.ColorTheme

private val ColorSchemeBP = ColorTheme(
    background = Black,
    lightBackground = LightBlack,
    additional = Grey,
    textColor = White,
    primary = PrimaryBP,
    secondary = SecondaryBP,
    tertiary = TertiaryBP,
    accent = AccentBP,
    gradient = GradientColorBP
)

private val ColorSchemeGC = ColorTheme(
    background = Black,
    lightBackground = LightBlack,
    additional = Grey,
    textColor = White,
    primary = PrimaryGC,
    secondary = SecondaryGC,
    tertiary = TertiaryGC,
    accent = AccentGC,
    gradient = GradientColorGC
)

private val ColorSchemeGP = ColorTheme(
    background = Black,
    lightBackground = LightBlack,
    additional = Grey,
    textColor = White,
    primary = PrimaryGP,
    secondary = SecondaryGP,
    tertiary = TertiaryGP,
    accent = AccentGP,
    gradient = GradientColorGP
)

private val ColorSchemePY = ColorTheme(
    background = Black,
    lightBackground = LightBlack,
    additional = Grey,
    textColor = White,
    primary = PrimaryPY,
    secondary = SecondaryPY,
    tertiary = TertiaryPY,
    accent = AccentPY,
    gradient = GradientColorPY
)

private val ColorSchemeGO = ColorTheme(
    background = Black,
    lightBackground = LightBlack,
    additional = Grey,
    textColor = White,
    primary = PrimaryGO,
    secondary = SecondaryGO,
    tertiary = TertiaryGO,
    accent = AccentGO,
    gradient = GradientColorGO
)

val ListColorTheme = listOf(ColorSchemeBP, ColorSchemeGC, ColorSchemeGP, ColorSchemePY, ColorSchemeGO)
