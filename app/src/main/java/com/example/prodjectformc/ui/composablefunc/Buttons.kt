package com.example.prodjectformc.ui.composablefunc

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Gray3
import com.example.prodjectformc.ui.theme.custom.Gray5
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.White

@Composable
fun ButtonBlueGrayMP(click: () -> Unit, modifier: Modifier, buttonIsBlue: Boolean, text: String){
    val colorBtnBlue = ButtonDefaults.outlinedButtonColors(
        contentColor = Color(Blue.value),
        containerColor = Color(Blue.value)
    )
    val colorBtnGray = ButtonDefaults.outlinedButtonColors(
        contentColor = Color(Gray3.value),
        containerColor = Color(Gray3.value)
    )
    Button(
        onClick = click,
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        colors = if (buttonIsBlue) colorBtnBlue else colorBtnGray
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            color = Color(0xFFFFFFFF),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ButtonBlueGrayWC(click: () -> Unit, modifier: Modifier, buttonIsBlue: Boolean, text: String){
    val colorBtnBlue = ButtonDefaults.outlinedButtonColors(
        contentColor = Color(Blue.value),
        containerColor = Color(Blue.value)
    )
    val colorBtnGray = ButtonDefaults.outlinedButtonColors(
        contentColor = Color(Gray5.value),
        containerColor = Color(Gray5.value)
    )
    Button(
        onClick = click,
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        colors = if (buttonIsBlue) colorBtnBlue else colorBtnGray
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
            color = Color(0xFFFFFFFF),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MaxWidthButton(text: String, onClick: () -> Unit, enabled: Boolean = true) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(White.value),
            containerColor = NewsTheme.colors.primary,
            disabledContainerColor = NewsTheme.colors.inversePrimary,
            disabledContentColor = Color(White.value)
        ),
        shape = RoundedCornerShape(15.dp),
        enabled = enabled
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}