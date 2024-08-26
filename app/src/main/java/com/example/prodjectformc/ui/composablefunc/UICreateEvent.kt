package com.example.prodjectformc.ui.composablefunc

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.White
import java.util.Calendar

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
                    .background(Color(White.value), shape = RoundedCornerShape(15.dp))
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = null) {
                        onClick(el)
                    }
                ,
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

@Composable
fun CustomDatePickerDialog(
    label: String,
    chosenYear: (Int) -> Unit,
    chosenMonth: (Int) -> Unit,
    chosenDay: (Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 5.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                DateSelectionSection(
                    onYearChosen = { chosenYear(it.toInt()) },
                    onMonthChosen = { chosenMonth(monthsNames.indexOf(it)+1) },
                    onDayChosen = { chosenDay(it.toInt()) },
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(Blue.value)
                    ),
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text(
                        text = "Готово",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun DateSelectionSection(
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
    onDayChosen: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Box(modifier = Modifier.weight(1f)){
            InfiniteItemsPicker(
                items = days,
                firstIndex = Int.MAX_VALUE / 2 + (currentDay - 2),
                onItemSelected =  onDayChosen
            )
        }
        Box(modifier = Modifier.weight(1f)){
            InfiniteItemsPicker(
                items = monthsNames,
                firstIndex = Int.MAX_VALUE / 2 - 4 + currentMonth,
                onItemSelected =  onMonthChosen
            )
        }
        Box(modifier = Modifier.weight(1f)){
            InfiniteItemsPicker(
                items = years,
                firstIndex = Int.MAX_VALUE / 2 + (currentYear - 1967),
                onItemSelected = onYearChosen
            )
        }
    }
}

@Composable
fun InfiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Column(modifier = Modifier
        .height(106.dp)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue.value = items[index]
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = items[index],
                        modifier = Modifier
                            .alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f)
                            .weight(1f),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                })
            }
        )
    }
}

val currentYear = Calendar.getInstance().get(Calendar.YEAR)
val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
val currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1

val years = (1950..2050).map { it.toString() }
val days = (1..31).map { it.toString() }
val monthsNames = listOf(
    "Январь",
    "Февраль",
    "Март",
    "Апрель",
    "Май",
    "Июнь",
    "Июль",
    "Август",
    "Сенятябрь",
    "Октябрь",
    "Ноябрь",
    "Декабрь"
)


