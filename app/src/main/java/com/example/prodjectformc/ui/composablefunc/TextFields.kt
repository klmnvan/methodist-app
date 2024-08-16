@file:Suppress("UNUSED_EXPRESSION")

package com.example.prodjectformc.ui.composablefunc

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.White

@Composable
fun TextFieldForm(value: String, input: (String) -> Unit, placeholder: String, clickOnIcon: () -> Unit, textFieldIsSelect: Boolean,
                  focusTextField: () -> Unit){
    var colorBorder = Color(Gray2.value)
    if (textFieldIsSelect) colorBorder = Color(Blue.value)
    OutlinedTextField(
        value = value,
        onValueChange = { input(it) },
        textStyle = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                Log.d("focus", focus.isFocused.toString())
                if(focus.isFocused) {
                    Log.d("focus", "Я зашёл в фокус")
                    focusTextField()
                }
            },
        placeholder = { Text(text = placeholder, style = MaterialTheme.typography.labelMedium) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(White.value),
            unfocusedContainerColor = Color(White.value),
            disabledContainerColor = Color(White.value),
            focusedBorderColor = Color(Blue.value),
            unfocusedBorderColor = colorBorder,
        ),
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = null) {
                        clickOnIcon()
                    },
                tint = Color(Gray1.value)
            )
        },
    )
}
