package com.example.prodjectformc.ui.theme.custom

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF1977FF)
val Blue80 = Color(0xCC1977FF)
val Blue20 = Color(0x334792FF)
val Black = Color(0xFF000000)
val Gray1 = Color(0xFFAAADB2)
val Gray2 = Color(0xFFF0F0F3)
val Gray5 = Color(0xFFA7A7A7)
val Gray3 = Color(0xFF555B69)
val DescriptionText = Color(0xFF939396)
val Orange = Color(0xFFFF7C3B)
val Orange80 = Color(0xCCFF7C3B)
val Outline = Color(0xFFF0F0F3)
val Purple = Color(0xFFC184FF)
val White = Color(0xFFFFFFFF)
val LightBack = Color(0xFFF7F7F9)
val DarkBack = Color(0xFF18181B)
val DarkContainers = Color(0xFF26272B)
val Green = Color(0xFF22B07D)
val DarkBackground = Color(0xFF272A37)
val DarkTextFiels = Color(0xFF323644)
val CustomTransparent = Color(0x00323644)

@Immutable
data class NewsColors(
    val primary: Color,
    val background: Color,
    val onBackground: Color,
)

val DarkColorScheme = darkColorScheme(
    background = DarkBack,
    primaryContainer = DarkContainers,
    onPrimary = White,
    onSecondary = DescriptionText,
    outline = Gray1,
    primary = Blue,
    inversePrimary = Gray3,
    error = Orange,
    onBackground = Gray1,
    surface = Gray1,
)

val LightColorScheme = lightColorScheme(
    background = LightBack,
    primaryContainer = White,
    onPrimary = Black,
    onSecondary = DescriptionText,
    outline = Outline,
    primary = Blue,
    inversePrimary = Gray3,
    error = Orange,
    onBackground = Gray3,
    surface = Gray1,
)

val SpaceColorScheme = NewsColors(
    primary = Purple,
    background = DarkBackground,
    onBackground = White,
)

val LocalNewsColors = staticCompositionLocalOf { LightColorScheme }