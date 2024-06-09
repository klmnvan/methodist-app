package com.example.prodjectformc.composablefunc

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TextNormal(text: String, modifier: Modifier?, fontSize: Int, color: Color){
    Text(
        text = text,
        modifier = modifier!!,
        color = color,
        fontSize = (fontSize-1).sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
fun TextBold(text: String, modifier: Modifier?, fontSize: Int, color: Color){
    Text(
        text = text,
        modifier = modifier!!,
        color = color,
        fontSize = (fontSize-1).sp,
        fontWeight = FontWeight.Bold
    )
}