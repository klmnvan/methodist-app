package com.example.prodjectformc.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerWidth(width: Dp){
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun SpacerHeight(height: Dp){
    Spacer(modifier = Modifier.height(height))
}