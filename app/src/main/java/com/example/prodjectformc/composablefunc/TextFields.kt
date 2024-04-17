package com.example.prodjectformc.composablefunc

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Black1
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.inter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthFieldComponent(value: String, input: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = value,
        onValueChange = { input(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp),
        textStyle = TextStyle(
            color = Color(Black.value)
        ),
        placeholder = { TextNormal(placeholder, Modifier, 14, Color(Black.value)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(Gray1.value),
            focusedBorderColor = Color(Gray1.value),
        )
    )
}