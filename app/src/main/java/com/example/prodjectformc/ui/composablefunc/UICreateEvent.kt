package com.example.prodjectformc.ui.composablefunc

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OptionsChooseFrom (list: List<String>, selected: String, onClick: (String) -> Unit ){
    FlowRow(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = Int.MAX_VALUE
    ) {
        list.forEach { el ->
            var colorBorder = Color(Gray2.value)
            if (el == selected) {
                colorBorder = Color(Blue.value)
            }
            Row(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = colorBorder,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .background(Color(White.value), shape = RoundedCornerShape(15.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (el == selected),
                    onClick = {
                        onClick(el)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(Blue.value),
                        unselectedColor = Color(Gray2.value)
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .padding(vertical = 8.dp),
                    text = el,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

