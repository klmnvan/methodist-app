package com.example.prodjectformc.ui.theme.custom

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Immutable
data class NewsShape(
    val shape15p: Shape = RoundedCornerShape(percent = 15),
    val shape30p: Shape = RoundedCornerShape(percent = 30),
)

val LocalNewsShapes = staticCompositionLocalOf { NewsShape() }
