package com.example.prodjectformc.ui.screen.createevent.participation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.composablefunc.OptionsChooseFrom
import com.example.prodjectformc.ui.composablefunc.TextFieldForm
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray5
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.White

@Composable
fun Participation(navHostController: NavHostController, viewModel: ParticipationViewModel = hiltViewModel()) {
    val state = viewModel.state
    viewModel.context = LocalContext.current
    var otherFormOfEvent by remember { mutableStateOf("")}
    val focusManager = LocalFocusManager.current
    var otherStatus by remember { mutableStateOf("")}
    var otherResult by remember { mutableStateOf("")}
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF7F7F9))
        .verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Пояснение формы участия",
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(modifier = Modifier.size(15.dp), onClick = { expanded = !expanded }) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        imageVector = if (expanded) ImageVector.vectorResource(R.drawable.button_collapse_text)
                        else ImageVector.vectorResource(R.drawable.button_expand_text),
                        contentDescription = if (expanded) "Свернуть" else "Развернуть",
                        tint = Color.Unspecified
                    )
                }
            }
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh)),
                exit = shrinkVertically(animationSpec = spring(stiffness = Spring.StiffnessHigh))
            ) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = ParagraphStyle(
                                    lineHeight = 20.sp
                                )
                            ) {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(Gray5.value)
                                    )
                                ) {
                                    append("Очное")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        color = Color(Gray5.value)
                                    )
                                ) {
                                    append(" - преподаватель непосредственно принимал участие в мероприятии и находился на площадке проведения мероприятия.\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(Gray5.value)
                                    )
                                ) {
                                    append("Заочное")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        color = Color(Gray5.value)
                                    )
                                ) {
                                    append("  - преподаватель отправил свои материалы организаторам мероприятия.\n")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = Color(Gray5.value)
                                    )
                                ) {
                                    append("Дистанционное")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = Raleway,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 14.sp,
                                        color = Color(Gray5.value)
                                    )
                                ) {
                                    append(" - преподаватель непосредственно принимал участие в мероприятии, но не находился на площадке проведения мероприятия, а использовал дистанционные технологии.")
                                }
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Форма участия",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listFormOfParticipation.isNotEmpty()) {
                OptionsChooseFrom(state.listFormOfParticipation, state.formOfParticipation) { el ->
                    viewModel.updateState(
                        state.copy(formOfParticipation = el)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Название мероприятия",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(state.name, { viewModel.updateState(viewModel.state.copy(name = it)) },
                "Концерт в актовом зале", {viewModel.updateState(viewModel.state.copy(name = ""))}, false, {} )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Форма мероприятия",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listFormOfEvent.isNotEmpty()) {
                OptionsChooseFrom(state.listFormOfEvent, state.formOfEvent) { el ->
                    viewModel.updateState(state.copy(formOfEvent = el))
                    focusManager.clearFocus()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Если ни один вариант в списке не подошёл, введите вручную", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(otherFormOfEvent,
                { viewModel.updateState(viewModel.state.copy(formOfEvent = it))
                    otherFormOfEvent = it },
                "Другая форма мероприятия",
                {
                    viewModel.updateState(state.copy(formOfEvent = ""))
                    otherFormOfEvent = ""}, otherFormOfEvent == state.formOfEvent,
                {
                    if(otherFormOfEvent == "") {
                        viewModel.updateState(state.copy(formOfEvent = ""))
                    }
                    else {
                        viewModel.updateState(state.copy(formOfEvent = otherFormOfEvent))

                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Статус мероприятия",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listStatus.isNotEmpty()) {
                OptionsChooseFrom(state.listStatus, state.status) {
                    viewModel.updateState(state.copy(status = it))
                    focusManager.clearFocus()
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Если ни один вариант в списке не подошёл, введите вручную", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(otherStatus,
                { viewModel.updateState(viewModel.state.copy(status = it))
                    otherStatus = it },
                "Другой статус мероприятия",
                {
                    viewModel.updateState(state.copy(status = ""))
                    otherStatus = ""}, otherStatus == state.status,
                {
                    if(otherStatus == "") {
                        viewModel.updateState(state.copy(status = ""))
                    }
                    else {
                        viewModel.updateState(state.copy(status = otherStatus))

                    }
                })
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Результат",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            if (state.listResult.isNotEmpty()) {
                OptionsChooseFrom(state.listResult, state.result) { el ->
                    viewModel.updateState(state.copy(result = el))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Если ни один вариант в списке не подошёл, введите вручную", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
            TextFieldForm(otherResult,
                { viewModel.updateState(viewModel.state.copy(result = it))
                    otherResult = it },
                "Другой результат",
                {
                    viewModel.updateState(state.copy(result = ""))
                    otherResult = ""}, otherResult == state.result,
                {
                    if(otherResult == "") {
                        viewModel.updateState(state.copy(result = ""))
                    }
                    else {
                        viewModel.updateState(state.copy(result = otherResult))

                    }
                })
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
                            Log.d("formOfParticipationtest", "${state.formOfParticipation}")
                            append("${state.formOfParticipation}, ${state.name}, ${state.formOfEvent}, ${state.status}, ${state.result}")
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
                        .clickable { viewModel.CreateParticipationEvent(navHostController) },
                    contentDescription = "",
                    tint = Color(
                        White.value)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}




