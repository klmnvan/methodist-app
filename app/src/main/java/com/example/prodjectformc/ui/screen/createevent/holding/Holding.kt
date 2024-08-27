package com.example.prodjectformc.ui.screen.createevent.holding

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.composablefunc.CustomDatePickerDialog
import com.example.prodjectformc.ui.composablefunc.OptionsChooseFrom
import com.example.prodjectformc.ui.composablefunc.TextFieldForm
import com.example.prodjectformc.ui.composablefunc.TextTittleForm
import com.example.prodjectformc.ui.composablefunc.TextTittleFormTextField
import com.example.prodjectformc.ui.composablefunc.currentDay
import com.example.prodjectformc.ui.composablefunc.currentMonth
import com.example.prodjectformc.ui.composablefunc.currentYear
import com.example.prodjectformc.ui.composablefunc.monthsNames
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Blue20
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.White
import com.example.prodjectformc.ui.theme.buttonTextStyle

@Composable
fun Holding(navHostController: NavHostController, viewModel: HoldingViewModel = hiltViewModel()){

    val state = viewModel.state
    viewModel.context = LocalContext.current
    var otherFormOfEvent by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var otherStatus by remember { mutableStateOf("") }
    var otherResult by remember { mutableStateOf("") }
    var chosenYear by remember { mutableIntStateOf(currentYear) }
    var chosenMonth by remember { mutableIntStateOf(currentMonth) }
    var chosenDay by remember { mutableIntStateOf(currentDay) }
    LaunchedEffect(chosenYear, chosenMonth, chosenDay) {
        viewModel.updateState(state.copy(dateOfEvent = "$chosenYear-${if(chosenMonth in 0..9) "0$chosenMonth" else chosenMonth }-${if(chosenDay in 0..9) "0$chosenDay" else chosenDay }"))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF7F7F9))
        .verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            TextTittleForm("Сведения")
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Название мероприятия")
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(state.name,
                { viewModel.updateState(viewModel.state.copy(name = it)) },
                "Название мероприятия",
                { viewModel.updateState(viewModel.state.copy(name = "")) },
                state.name.isNotEmpty(),
                {})
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Форма мероприятия")
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listFormOfEvent.isNotEmpty()) {
                OptionsChooseFrom(state.listFormOfEvent, state.formOfEvent) { el ->
                    viewModel.updateState(state.copy(formOfEvent = el))
                    focusManager.clearFocus()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Если ни один вариант в списке не подошёл, введите вручную",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(otherFormOfEvent,
                {
                    viewModel.updateState(viewModel.state.copy(formOfEvent = it))
                    otherFormOfEvent = it
                },
                "Другая форма мероприятия",
                {
                    viewModel.updateState(state.copy(formOfEvent = ""))
                    otherFormOfEvent = ""
                }, otherFormOfEvent == state.formOfEvent,
                {
                    if (otherFormOfEvent == "") {
                        viewModel.updateState(state.copy(formOfEvent = ""))
                    } else {
                        viewModel.updateState(state.copy(formOfEvent = otherFormOfEvent))

                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Место проведения")
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(state.location,
                { viewModel.updateState(viewModel.state.copy(location = it)) },
                "Место проведения",
                { viewModel.updateState(viewModel.state.copy(location = "")) },
                state.location.isNotEmpty(),
                {})
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Статус мероприятия")
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listStatus.isNotEmpty()) {
                OptionsChooseFrom(state.listStatus, state.status) {
                    viewModel.updateState(state.copy(status = it))
                    focusManager.clearFocus()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Если ни один вариант в списке не подошёл, введите вручную",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(otherStatus,
                {
                    viewModel.updateState(viewModel.state.copy(status = it))
                    otherStatus = it
                },
                "Другой статус мероприятия",
                {
                    viewModel.updateState(state.copy(status = ""))
                    otherStatus = ""
                }, otherStatus == state.status,
                {
                    if (otherStatus == "") {
                        viewModel.updateState(state.copy(status = ""))
                    } else {
                        viewModel.updateState(state.copy(status = otherStatus))

                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Результат")
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listResult.isNotEmpty()) {
                OptionsChooseFrom(state.listResult, state.result) { el ->
                    viewModel.updateState(state.copy(result = el))
                    focusManager.clearFocus()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Если ни один вариант в списке не подошёл, введите вручную",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(otherResult,
                {
                    viewModel.updateState(viewModel.state.copy(result = it))
                    otherResult = it
                },
                "Другой результат",
                {
                    viewModel.updateState(state.copy(result = ""))
                    otherResult = ""
                }, otherResult == state.result,
                {
                    if (otherResult == "") {
                        viewModel.updateState(state.copy(result = ""))
                    } else {
                        viewModel.updateState(state.copy(result = otherResult))

                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Дата")
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "$chosenDay ${monthsNames[chosenMonth - 1]} $chosenYear",
                    modifier = Modifier
                        .background(Color(Blue20.value), RoundedCornerShape(15.dp))
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(Blue.value)
                )
                var showDialog by remember { mutableStateOf(false) }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(1f),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(Blue.value)
                    ),
                    onClick = {
                        showDialog = true
                    }) {
                    Icon(
                        modifier = Modifier.padding(),
                        imageVector = ImageVector.vectorResource(R.drawable.icon_calendar),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Изменить", style = buttonTextStyle)
                }

                if (showDialog) {
                    CustomDatePickerDialog(
                        "Выбор даты",
                        { chosenYear = it },
                        { chosenMonth = it },
                        { chosenDay = it }) {
                        showDialog = false
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                fontFamily = Raleway,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        ) {
                            append("Выбрано: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = Raleway,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        ) {
                            append("${state.name}, ${state.formOfEvent}, ${state.location}, ${state.status}, ${state.result}, дата: ${state.dateOfEvent}")
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.button_next),
                    modifier = Modifier
                        .background(Color(Blue.value), shape = RoundedCornerShape(15.dp))
                        .size(45.dp)
                        .padding(12.dp)
                        .clickable { viewModel.createEvent(navHostController) },
                    contentDescription = "",
                    tint = Color(
                        White.value
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}