package com.example.prodjectformc.ui.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.event.EventModel
import com.example.prodjectformc.ui.composablefunc.TextDescFragment
import com.example.prodjectformc.ui.composablefunc.TextTittleFragment
import com.example.prodjectformc.ui.theme.Poppins
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.convertDate
import com.example.prodjectformc.ui.theme.convertDateDefaultFormat
import com.example.prodjectformc.ui.theme.custom.Black
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Blue20
import com.example.prodjectformc.ui.theme.custom.Blue80
import com.example.prodjectformc.ui.theme.custom.Gray1
import com.example.prodjectformc.ui.theme.custom.Green
import com.example.prodjectformc.ui.theme.custom.LightBack
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.Orange
import com.example.prodjectformc.ui.theme.custom.Purple
import com.example.prodjectformc.ui.theme.custom.White
import com.example.prodjectformc.ui.theme.firstCharUp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController?, viewModel: HomeViewModel = hiltViewModel()){
    val state = viewModel.state
    viewModel.context = LocalContext.current
    var selectedCategory by remember { mutableStateOf("Всё") }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(NewsTheme.colors.background)) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
            ) {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 4.dp, shape = RoundedCornerShape(30), spotColor = Color(
                            Black.value
                        )
                    )
                    .background(
                        color = NewsTheme.colors.primaryContainer,
                        shape = RoundedCornerShape(30)
                    )
                    .padding(14.dp)
            ) {
                Row(modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_search),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp),
                        tint = NewsTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Divider(color = NewsTheme.colors.primary, modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .padding(vertical = 5.dp))
                    Spacer(modifier = Modifier.width(14.dp))
                    Box(modifier = Modifier.weight(1f),) {
                        BasicTextField(
                            value = state.searchText,
                            onValueChange = { viewModel.updateState(viewModel.state.copy(searchText = it)) },
                            textStyle = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary),
                            singleLine = true,
                            maxLines = 1
                        )
                        if (state.searchText.isEmpty()) {
                            Text(text = "Поиск", style = NewsTheme.typography.labelMedium.copy(color = NewsTheme.colors.onSecondary))
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                            .fillMaxWidth()
                            .clickable {
                                viewModel.updateState(viewModel.state.copy(searchText = ""))
                            },
                        tint = NewsTheme.colors.surface
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Категории", style = NewsTheme.typography.displayLarge.copy(color = NewsTheme.colors.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                if(state.listEvents != null){
                    val listCategory = state.listEvents!!.map { it.formOfWork?.name }.distinct().toMutableList()
                    listCategory.add(0, "Всё")
                    for(event in listCategory){
                        val isSelected = selectedCategory == event
                        val backgroundColor = if (isSelected) Color(Blue.value) else Color(Blue20.value)
                        val textColor = if (isSelected) Color(White.value) else Color(Blue.value)
                        Text(modifier = Modifier
                            .background(backgroundColor, RoundedCornerShape(15.dp))
                            .padding(vertical = 14.dp, horizontal = 20.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                selectedCategory = event!!
                            }, text = event!!, fontSize = 12.sp,
                            fontFamily = Raleway, fontWeight = FontWeight.SemiBold,
                            color = textColor)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Пройденные мероприятия", style = NewsTheme.typography.displayLarge.copy(color = NewsTheme.colors.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))
            if(state.listEvents != null){
                val pattern = Regex(state.searchText, RegexOption.IGNORE_CASE)
                for (event in state.listEvents!!.filter { if(selectedCategory != "Всё") it.formOfWork!!.name.contains(selectedCategory)
                else it.formOfWork!!.name.contains("")}.filter { pattern.containsMatchIn(it.specifications.toString()) }){
                    var title by remember { mutableStateOf("") }
                    var desc by remember { mutableStateOf("") }
                    desc = event.specifications.formOfEvent!!
                    if(desc == "") desc = event.specifications.place!!
                    if(desc == "") desc = "Количество часов: ${event.specifications.quantityOfHours.toString()}"
                    if(desc == "") desc = event.specifications.location!!
                    title = event.specifications.name.toString()
                    if(title == "") title = event.specifications.location!!
                    var showDialog by remember { mutableStateOf(false) }
                    Column (modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(15),
                            spotColor = Color(Black.value)
                        )
                        .clickable {
                            showDialog = true
                        }
                        .background(color = NewsTheme.colors.primaryContainer, shape = RoundedCornerShape(15))
                        .padding(vertical = 16.dp, horizontal = 18.dp)) {
                        Row (verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.icon_event),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .fillMaxWidth(),
                                tint = getColorIcon(category = event.formOfWork!!.name)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)) {
                                Text(text = title.firstCharUp(),
                                    modifier = Modifier
                                        .padding(bottom = 2.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = Raleway,
                                    style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary))
                                Text(text = event.formOfWork.name.firstCharUp(),
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Raleway,
                                    fontSize = 12.sp,
                                    style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.surface),)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = event.dateOfEvent.convertDate(),
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(top = 3.dp),
                                fontWeight = FontWeight.Medium,
                                fontFamily = Poppins,
                                fontSize = 12.sp,
                                style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary))
                        }
                        Divider(modifier = Modifier.padding(vertical = 10.dp), color = NewsTheme.colors.primary, thickness = 0.5.dp)
                        Text(text = desc.firstCharUp(), style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary),)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    if (showDialog) {
                        ShowFragment(title, event, getColorIcon(event.formOfWork!!.name), viewModel){
                            showDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowFragment(title: String, event: EventModel, primaryColor: Color, viewModel: HomeViewModel, onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = NewsTheme.colors.primaryContainer
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_event),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .fillMaxWidth(),
                        tint = primaryColor
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)) {
                        Text(text = title.firstCharUp(),
                            modifier = Modifier
                                .padding(bottom = 2.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Raleway,
                            style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary))
                        Text(text = event.formOfWork!!.name.firstCharUp(),
                            fontWeight = FontWeight.Normal,
                            fontFamily = Raleway,
                            fontSize = 12.sp,
                            color = primaryColor,
                            style = NewsTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.Top)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onDismissRequest()
                            },
                        tint = NewsTheme.colors.surface
                    )
                }
                GradientDivider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp), thickness = 2.dp,
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1977FF), Color(0xFF53B9F5))
                    ))
                Column(modifier = Modifier.fillMaxWidth()) {
                    with(event.specifications){
                        if(formOfParticipation != ""){
                            TextTittleFragment("Форма участия:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.formOfParticipation!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(type != ""){
                            TextTittleFragment("Вид:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.type!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(name != ""){
                            TextTittleFragment("Название:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.name!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(formOfEvent != ""){
                            TextTittleFragment("Форма мероприятия:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.formOfEvent!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(location != ""){
                            TextTittleFragment("Место:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.location!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(status != ""){
                            TextTittleFragment("Статус мероприятия:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.status!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(result != ""){
                            TextTittleFragment("Результат:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.result!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(quantityOfHours != null){
                            TextTittleFragment("Количество часов:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.quantityOfHours!!.toString())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if(place != ""){
                            TextTittleFragment("Место публикации:")
                            Spacer(modifier = Modifier.height(4.dp))
                            TextDescFragment(event.specifications.place!!.firstCharUp())
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                    TextTittleFragment("Дата:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.dateOfEvent.convertDateDefaultFormat())
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.fillMaxWidth(1f),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
                            event.id?.let { viewModel.deleteEvent(it) }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NewsTheme.colors.error,
                            contentColor = Color(White.value)
                        )){
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_basket),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .size(20.dp),
                            tint = Color(White.value)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun GradientDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    gradient: Brush
) {
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(thickness)) {
        drawRect(brush = gradient)
    }
}

@Composable
fun getColorIcon(category: String): Color {
    return when(category){
        ("Участие") ->  {
            Color(Green.value)
        }
        ("Проведение") ->  {
            Color(Blue.value)
        }
        ("Стажировка") ->  {
            Color(Purple.value)
        }
        ("Публикация") ->  {
            Color(Orange.value)
        }
        else -> {
            Color(Blue80.value)
        }

    }
}

