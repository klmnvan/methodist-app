package com.example.prodjectformc.ui.composablefunc

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Raleway

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

@Composable
fun TextTittleFormTextField(text: String){
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = Color(Black.value)
    )
    /*TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(Black.value),
    ),*/
}

@Composable
fun TextTittleForm(text: String){
    Text(text = text, fontSize = 32.sp, style = MaterialTheme.typography.titleLarge)
}