package com.example.prodjectformc.ui.screen.createevent.publication

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
import androidx.compose.material3.ButtonColors
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
import com.example.prodjectformc.data.model.general.FormOfWork
import com.example.prodjectformc.ui.composablefunc.CustomDatePickerDialog
import com.example.prodjectformc.ui.composablefunc.TextFieldForm
import com.example.prodjectformc.ui.composablefunc.TextTittleForm
import com.example.prodjectformc.ui.composablefunc.TextTittleFormTextField
import com.example.prodjectformc.ui.composablefunc.currentDay
import com.example.prodjectformc.ui.composablefunc.currentMonth
import com.example.prodjectformc.ui.composablefunc.currentYear
import com.example.prodjectformc.ui.composablefunc.monthsNames
import com.example.prodjectformc.ui.screen.createevent.participation.ParticipationViewModel
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Blue20
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.White
import com.example.prodjectformc.ui.theme.buttonTextStyle

@Composable
fun Publication(navHostController: NavHostController, viewModel: PublicationViewModel = hiltViewModel()){
    val state = viewModel.state
    viewModel.context = LocalContext.current
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
            Spacer(modifier = Modifier.height(12.dp))
            TextTittleFormTextField("Вид")
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(state.type, { viewModel.updateState(viewModel.state.copy(type = it)) },
                "Вид публикации", {viewModel.updateState(viewModel.state.copy(type = ""))}, state.type.isNotEmpty(), {} )
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Название публикации")
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(state.name, { viewModel.updateState(viewModel.state.copy(name = it)) },
                "Название публикации", {viewModel.updateState(viewModel.state.copy(name = ""))}, state.name.isNotEmpty(), {} )
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Место публикации")
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(state.place, { viewModel.updateState(viewModel.state.copy(place = it)) },
                "Место публикации", {viewModel.updateState(viewModel.state.copy(place = ""))}, state.place.isNotEmpty(), {} )
            Spacer(modifier = Modifier.height(20.dp))
            TextTittleFormTextField("Дата")
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "$chosenDay ${monthsNames[chosenMonth-1]} $chosenYear",
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
                    Icon(modifier = Modifier.padding(),
                        imageVector = ImageVector.vectorResource(R.drawable.icon_calendar), contentDescription = "",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = "Изменить", style = buttonTextStyle)
                }

                if (showDialog) {
                    CustomDatePickerDialog("Выбор даты", {chosenYear = it}, {chosenMonth = it}, {chosenDay = it}) {
                        showDialog = false
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                fontFamily = Raleway,
                                fontSize = 16.sp,
                                color = Color.Black)
                        ){
                            append("Выбрано: ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = Raleway,
                                fontSize = 16.sp,
                                color = Color.Black)
                        ) {
                            append("${state.name}, ${state.place}, дата: ${state.dateOfEvent}")
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(imageVector = ImageVector.vectorResource(R.drawable.button_next),
                    modifier = Modifier
                        .background(Color(Blue.value), shape = RoundedCornerShape(15.dp))
                        .size(45.dp)
                        .padding(12.dp)
                        .clickable { viewModel.CreatePublicationEvent(navHostController) },
                    contentDescription = "",
                    tint = Color(
                        White.value)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}