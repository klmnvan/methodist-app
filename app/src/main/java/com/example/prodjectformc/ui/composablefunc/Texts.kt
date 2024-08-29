package com.example.prodjectformc.ui.composablefunc

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.custom.NewsTheme

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
        color = NewsTheme.colors.onPrimary
    )
    /*TextStyle(
        fontFamily = Raleway,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Color(Black.value),
    ),*/
}

@Composable
fun TextTittleFragment(text: String){
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = NewsTheme.colors.onPrimary
    )
}

@Composable
fun TextDescFragment(text: String){
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = NewsTheme.colors.onSecondary
    )
}

@Composable
fun TextTittleForm(text: String){
    Text(text = text, fontSize = 32.sp, style = NewsTheme.typography.titleLarge.copy(color = NewsTheme.colors.onPrimary))
}