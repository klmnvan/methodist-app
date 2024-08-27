package com.example.prodjectformc.ui.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.ui.composablefunc.CustomDatePickerDialog
import com.example.prodjectformc.ui.composablefunc.TextDescFragment
import com.example.prodjectformc.ui.composablefunc.TextTittleFragment
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Blue20
import com.example.prodjectformc.ui.theme.Blue80
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Green
import com.example.prodjectformc.ui.theme.Orange
import com.example.prodjectformc.ui.theme.Poppins
import com.example.prodjectformc.ui.theme.Purple
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.White
import com.example.prodjectformc.ui.theme.WhiteHomeBack
import com.example.prodjectformc.ui.theme.convertDate
import com.example.prodjectformc.ui.theme.convertDateDefaultFormat
import com.example.prodjectformc.ui.theme.firstCharUp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController?, viewModel: HomeViewModel = hiltViewModel()){
    val state = viewModel.state
    val scrollState = rememberScrollState()
    var selectedCategory by remember { mutableStateOf("Всё") }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color(WhiteHomeBack.value))) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
            ) {
            Spacer(modifier = Modifier.height(24.dp))
            Box {
                Box(modifier = Modifier
                    .fillMaxWidth(0.985f)
                    .shadow(
                        elevation = 5.dp, shape = RoundedCornerShape(30), spotColor = Color(
                            Black.value
                        ), ambientColor = Color(Black.value)
                    )
                    .background(color = Color.Transparent, shape = RoundedCornerShape(30))
                    .padding(horizontal = 5.dp)
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        /*.shadow(
                            elevation = 10.dp, shape = RoundedCornerShape(30), spotColor = Color(
                                Black.value
                            )
                        )*/
                        .background(color = Color.White, shape = RoundedCornerShape(30))
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
                            tint = Color(Gray1.value)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Divider(color = Color(Blue.value), modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .padding(vertical = 5.dp))
                        Spacer(modifier = Modifier.width(14.dp))
                        Box(modifier = Modifier.weight(1f),) {
                            BasicTextField(
                                value = state.searchText,
                                onValueChange = { viewModel.state = viewModel.state.copy(searchText = it) },
                                textStyle = MaterialTheme.typography.titleMedium,
                                singleLine = true,
                                maxLines = 1
                            )
                            if (state.searchText.isEmpty()) {
                                Text(text = "Поиск", style = MaterialTheme.typography.labelMedium)
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
                                    viewModel.state = viewModel.state.copy(searchText = "")
                                },
                            tint = Color(Gray1.value)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Категории", style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                if(CurrentUser.listEvents != null){
                    val listCategory = CurrentUser.listEvents!!.map { it.formOfWork?.name }.distinct().toMutableList()
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
            Text(text = "Пройденные мероприятия", style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(8.dp))
            if(CurrentUser.listEvents != null){
                for (event in CurrentUser.listEvents!!.filter { if(selectedCategory != "Всё") it.formOfWork!!.name.contains(selectedCategory)
                else it.formOfWork!!.name.contains("")}) {
                    var title by remember { mutableStateOf("") }
                    var desc by remember { mutableStateOf("") }
                    desc = event.specifications.formOfEvent!!
                    if(desc == "") desc = event.specifications.place!!
                    if(desc == "") desc = "Количество часов: ${event.specifications.quantityOfHours.toString()}"
                    if(desc == "") desc = event.specifications.location!!
                    title = event.specifications.name.toString()
                    if(event.specifications.name == "") title = event.specifications.location!!
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
                        .background(color = Color(White.value), shape = RoundedCornerShape(15))
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
                                    style = MaterialTheme.typography.titleMedium)
                                Text(text = event.formOfWork.name.firstCharUp(),
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = Raleway,
                                    fontSize = 12.sp,
                                    color = Color(Gray1.value),
                                    style = MaterialTheme.typography.titleMedium,)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = event.dateOfEvent.convertDate(),
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(top = 3.dp),
                                fontWeight = FontWeight.Medium,
                                fontFamily = Poppins,
                                fontSize = 12.sp,
                                style = MaterialTheme.typography.titleMedium)
                        }
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        Text(text = desc.firstCharUp(), style = MaterialTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                    if (showDialog) {
                        if (event.formOfWork?.name == "Стажировка"){
                            ShowFragmentEventInternship(event, getColorIcon(event.formOfWork.name)) {
                                showDialog = false
                            }
                        }
                        if (event.formOfWork?.name == "Проведение"){
                            ShowFragmentEventHolding(event, getColorIcon(event.formOfWork.name)) {
                                showDialog = false
                            }
                        }
                        if (event.formOfWork?.name == "Участие"){
                            ShowFragmentEventParticipation(event, getColorIcon(event.formOfWork.name)) {
                                showDialog = false
                            }
                        }
                        if (event.formOfWork?.name == "Публикация"){
                            ShowFragmentEventPublication(event, getColorIcon(event.formOfWork.name)) {
                                showDialog = false
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowFragmentEventInternship(event: EventModel, primaryColor: Color, onDismissRequest: () -> Unit){
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
                        Text(text = event.specifications.location!!.firstCharUp(),
                            modifier = Modifier
                                .padding(bottom = 2.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Raleway,
                            style = MaterialTheme.typography.titleMedium)
                        Text(text = event.formOfWork!!.name.firstCharUp(),
                            fontWeight = FontWeight.Normal,
                            fontFamily = Raleway,
                            fontSize = 12.sp,
                            color = primaryColor,
                            style = MaterialTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Top)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onDismissRequest()
                            },
                        tint = Color(Gray1.value)
                    )
                }
                GradientDivider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp), thickness = 2.dp,
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1977FF), Color(0xFF53B9F5))
                    ))
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextTittleFragment("Место прохождения:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.location!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Дата прохождения:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.dateOfEvent.convertDateDefaultFormat())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Количество часов:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.quantityOfHours!!.toString())
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.fillMaxWidth(1f),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(Orange.value),
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
fun ShowFragmentEventPublication(event: EventModel, primaryColor: Color, onDismissRequest: () -> Unit){
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
                        Text(text = event.specifications.name!!.firstCharUp(),
                            modifier = Modifier
                                .padding(bottom = 2.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Raleway,
                            style = MaterialTheme.typography.titleMedium)
                        Text(text = event.formOfWork!!.name.firstCharUp(),
                            fontWeight = FontWeight.Normal,
                            fontFamily = Raleway,
                            fontSize = 12.sp,
                            color = primaryColor,
                            style = MaterialTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Top)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onDismissRequest()
                            },
                        tint = Color(Gray1.value)
                    )
                }
                GradientDivider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp), thickness = 2.dp,
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1977FF), Color(0xFF53B9F5))
                    ))
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextTittleFragment("Вид:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.type!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Название:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.name!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Место публикации:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.place!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Дата:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.dateOfEvent.convertDateDefaultFormat())
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.fillMaxWidth(1f),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(Orange.value),
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
fun ShowFragmentEventHolding(event: EventModel, primaryColor: Color, onDismissRequest: () -> Unit){
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
                        Text(text = event.specifications.name!!.firstCharUp(),
                            modifier = Modifier
                                .padding(bottom = 2.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Raleway,
                            style = MaterialTheme.typography.titleMedium)
                        Text(text = "Проведение мероприятия",
                            fontWeight = FontWeight.Normal,
                            fontFamily = Raleway,
                            fontSize = 12.sp,
                            color = primaryColor,
                            style = MaterialTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Top)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onDismissRequest()
                            },
                        tint = Color(Gray1.value)
                    )
                }
                GradientDivider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp), thickness = 2.dp,
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1977FF), Color(0xFF53B9F5))
                    ))
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextTittleFragment("Название мероприятия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.name!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Форма мероприятия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.formOfEvent!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Место проведения:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.location!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Статус мероприятия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.status!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Результат:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.result!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Дата прохождения:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.dateOfEvent.convertDateDefaultFormat())
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.fillMaxWidth(1f),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(Orange.value),
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
fun ShowFragmentEventParticipation(event: EventModel, primaryColor: Color, onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(vertical = 20.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth().verticalScroll(rememberScrollState())
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
                        Text(text = event.specifications.name!!.firstCharUp(),
                            modifier = Modifier
                                .padding(bottom = 2.dp),
                            fontWeight = FontWeight.Bold,
                            fontFamily = Raleway,
                            style = MaterialTheme.typography.titleMedium)
                        Text(text = "Участие в мероприятии",
                            fontWeight = FontWeight.Normal,
                            fontFamily = Raleway,
                            fontSize = 12.sp,
                            color = primaryColor,
                            style = MaterialTheme.typography.titleMedium,)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_clear),
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Top)
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onDismissRequest()
                            },
                        tint = Color(Gray1.value)
                    )
                }
                GradientDivider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp), thickness = 2.dp,
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF1977FF), Color(0xFF53B9F5))
                    ))
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextTittleFragment("Форма участия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.formOfParticipation!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Название мероприятия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.name!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Форма мероприятия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.formOfEvent!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Статус мероприятия:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.status!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Результат:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.specifications.result!!.firstCharUp())
                    Spacer(modifier = Modifier.height(12.dp))
                    TextTittleFragment("Дата прохождения:")
                    Spacer(modifier = Modifier.height(4.dp))
                    TextDescFragment(event.dateOfEvent.convertDateDefaultFormat())
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(modifier = Modifier.fillMaxWidth(1f),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(Orange.value),
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

