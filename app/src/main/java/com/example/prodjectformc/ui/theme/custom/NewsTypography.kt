package com.example.prodjectformc.ui.theme.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.prodjectformc.ui.theme.Poppins
import com.example.prodjectformc.ui.theme.Raleway

@Immutable
data class NewsTypography(
    val bodyLarge: TextStyle,
    val titleLarge: TextStyle,
    val displayLarge: TextStyle,
    val bodyMedium: TextStyle,
    val labelMedium: TextStyle,
    val headlineMedium: TextStyle,
    val blueTextPoppins: TextStyle,
    val buttonTextStyle: TextStyle,
    val titleMedium: TextStyle
)

val newsTypography = NewsTypography(
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color(Black.value),
    ),
    titleLarge = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(Black.value),
    ),
    displayLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color(Black.value),
    ),
    bodyMedium = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color(Black.value),
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color(Gray1.value)
    ),
    titleMedium = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color(Black.value)
    ),
    headlineMedium = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = Color(Gray5.value)
    ),
    buttonTextStyle = TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    blueTextPoppins = TextStyle(
    fontFamily = Poppins,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
    )
)

val LocalNewsTypography = staticCompositionLocalOf { newsTypography }