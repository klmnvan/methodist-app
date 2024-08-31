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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.theme.custom.Black
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Gray1
import com.example.prodjectformc.ui.theme.custom.Gray2
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.White

@Composable
fun TextFieldForm(value: String, input: (String) -> Unit, placeholder: String, clickOnIcon: () -> Unit, textFieldIsSelect: Boolean,
                  focusTextField: () -> Unit){
    var colorBorder = Color(Gray2.value)
    if (textFieldIsSelect) colorBorder = Color(Blue.value)
    OutlinedTextField(
        value = value,
        onValueChange = { input(it) },
        textStyle = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                Log.d("focus", focus.isFocused.toString())
                if(focus.isFocused) {
                    Log.d("focus", "Я зашёл в фокус")
                    focusTextField()
                }
            },
        placeholder = { Text(text = placeholder, style = NewsTheme.typography.labelMedium.copy(color = NewsTheme.colors.onSecondary)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = NewsTheme.colors.primaryContainer,
            unfocusedContainerColor = NewsTheme.colors.primaryContainer,
            disabledContainerColor = NewsTheme.colors.primaryContainer,
            focusedBorderColor = NewsTheme.colors.primary,
            unfocusedBorderColor = colorBorder,
            cursorColor = NewsTheme.colors.primary
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
                tint = NewsTheme.colors.surface
            )
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldAuth(value: String, input: (String) -> Unit, placeholder: String, clickOnIcon: () -> Unit){
    OutlinedTextField(
        value = value,
        onValueChange = {input(it) },
        textStyle = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary, fontSize = 14.sp),
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp, shape = RoundedCornerShape(30), spotColor = Color(
                    Black.value
                )
            ),
        placeholder = { Text(text = placeholder, style = NewsTheme.typography.labelMedium.copy(color = NewsTheme.colors.onSecondary, fontSize = 14.sp)) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            containerColor = NewsTheme.colors.primaryContainer,
            cursorColor = NewsTheme.colors.primary
        ),
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        clickOnIcon()
                    },
                tint = NewsTheme.colors.surface
            )
        },
    )
}
