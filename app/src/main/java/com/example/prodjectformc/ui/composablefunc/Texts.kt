package com.example.prodjectformc.ui.composablefunc

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.custom.Gray5
import com.example.prodjectformc.ui.theme.custom.NewsTheme

@Composable
fun TextTittle(text: String){
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = NewsTheme.colors.onPrimary
    )
}

@Composable
fun TextDescription(text: String){
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = NewsTheme.colors.onSecondary
    )
}

@Composable
fun TextTittleAuth(text: String){
    Text(
        text = text,
        fontFamily = Raleway,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = NewsTheme.colors.onPrimary
    )
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