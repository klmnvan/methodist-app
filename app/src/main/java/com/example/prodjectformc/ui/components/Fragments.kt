package com.example.prodjectformc.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.custom.NewsTheme

@Composable
fun FrgmError(message: String, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.padding(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = NewsTheme.colors.primaryContainer,
            ),
        ) {
            Column {
                Text(text = "Ошибка",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Raleway,
                    style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary))
                Spacer(modifier = Modifier.height(12.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.icon_error),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(24.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onDismissRequest()
                        },
                    tint = NewsTheme.colors.surface
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = message,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Raleway,
                    fontSize = 12.sp,
                    style = NewsTheme.typography.titleMedium)
            }
        }
    }
}