package com.example.prodjectformc.ui.screen.createevent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateEvent(navHostController: NavHostController, viewModel: CreateEventViewModel = hiltViewModel()){



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F9))
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Начнём..", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            val progress by remember {  mutableFloatStateOf(0.1f) }
            LinearProgressIndicator(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth(),
                progress = progress,
                color = Color(Blue.value),
                trackColor = Color(0xFFD9D9D9),
                strokeCap = StrokeCap.Round
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Одно заполнение формы отражает одно мероприятие.\n" +
                    "Если Вы проводили/принимали участие в нескольких мероприятиях, то необходимо отправить данные несколько раз",
                style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Выберите форму работы", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(12.dp))
            val radioOptions = listOf("Проведение мероприятия","Участие в мероприятии","Публикация","Стажировка")
            var selectedOption by remember { mutableStateOf(radioOptions[0]) }
            Column {
                FlowRow(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp), maxItemsInEachRow = Int.MAX_VALUE){
                    radioOptions.forEach { typeOfWork ->
                        var colorBorder = Color(Gray2.value)
                        if(typeOfWork == selectedOption){
                            colorBorder = Color(Blue.value)
                        }
                        Row(modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = colorBorder,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(Color(White.value), shape = RoundedCornerShape(15.dp)),verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = (typeOfWork == selectedOption),
                                onClick = { selectedOption = typeOfWork },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(Blue.value),
                                    unselectedColor = Color(Gray2.value)
                                )
                            )
                            Text(
                                modifier = Modifier.padding(end = 24.dp),
                                text = typeOfWork,
                                style = MaterialTheme.typography.bodyMedium
                            )
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
                                    color = Color.Black)){
                                append("Выбрано: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = Raleway,
                                    fontSize = 16.sp,
                                    color = Color.Black)
                            ) {
                                append(selectedOption.toLowerCase())
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.button_next),
                        modifier = Modifier
                            .background(Color(Blue.value), shape = RoundedCornerShape(15.dp))
                            .size(45.dp)
                            .padding(12.dp),
                        contentDescription = "",
                        tint = Color(
                            White.value)
                    )
                }

            }
        }
    }
}
