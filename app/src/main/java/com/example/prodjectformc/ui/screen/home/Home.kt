package com.example.prodjectformc.ui.screen.home

import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.event.EventModel
import com.example.prodjectformc.ui.components.TextDescFragment
import com.example.prodjectformc.ui.components.TextFiledSesrch
import com.example.prodjectformc.ui.components.TextTittleFragment
import com.example.prodjectformc.ui.theme.Poppins
import com.example.prodjectformc.ui.theme.Raleway
import com.example.prodjectformc.ui.theme.convertDate
import com.example.prodjectformc.ui.theme.convertDateDefaultFormat
import com.example.prodjectformc.ui.theme.custom.Black
import com.example.prodjectformc.ui.theme.custom.Blue
import com.example.prodjectformc.ui.theme.custom.Blue20
import com.example.prodjectformc.ui.theme.custom.Blue80
import com.example.prodjectformc.ui.theme.custom.Green
import com.example.prodjectformc.ui.theme.custom.NewsTheme
import com.example.prodjectformc.ui.theme.custom.Orange
import com.example.prodjectformc.ui.theme.custom.Purple
import com.example.prodjectformc.ui.theme.custom.White
import com.example.prodjectformc.ui.theme.firstCharUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun Home(navHostController: NavHostController?, viewModel: HomeViewModel = hiltViewModel()){
    val state = viewModel.state.collectAsState()
    viewModel.context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val items: MutableState<MutableList<EventModel>> = remember { mutableStateOf(mutableListOf()) }
    viewModel.filteredListEvents()

    LaunchedEffect(key1 = viewModel.filteredListEvent) {
        viewModel.filteredListEvent.collect {
            listEvent -> listEvent.let {
            items.value = listEvent
            }
        }
    }

    LaunchedEffect(state.value.sortedType) {
        withContext(Dispatchers.IO) {
            if(state.value.listEvents.isNotEmpty()){
                viewModel.stateValue = state.value.copy(listEvents = sortedEvents(state.value.listEvents, state.value.sortedType))
            }
        }
    }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO){
            viewModel.launch()
        }
    }
    Box (modifier = Modifier.fillMaxSize().background(NewsTheme.colors.background)) {
        Column (modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            TextFiledSesrch(state.value.searchText, { viewModel.stateValue = state.value.copy(searchText = it) }, "Поиск") {
                viewModel.stateValue = state.value.copy(searchText = "")
                focusManager.clearFocus()
            }
            Spacer(modifier = Modifier.height(16.dp))
            var expanded by remember { mutableStateOf(false) }
            Column {
                Row(modifier = Modifier.fillMaxWidth().clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = null) { expanded = !expanded },
                    verticalAlignment = Alignment.CenterVertically )
                {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_sorted),
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp),
                        tint = NewsTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(color = NewsTheme.colors.onPrimary)) {
                                append("Сортировка: ")
                            }
                            withStyle(SpanStyle(color = NewsTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                append(state.value.listSortedType[state.value.sortedType])
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14.sp,
                        style = NewsTheme.typography.bodyMedium
                    )
                }
                DropdownMenu(
                    modifier = Modifier.background(NewsTheme.colors.primaryContainer),
                    offset = DpOffset(0.dp, 8.dp),
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    state.value.listSortedType.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                viewModel.stateValue.sortedType = state.value.listSortedType.indexOf(item)
                                expanded = false
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = NewsTheme.colors.onPrimary,
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Divider(thickness = 0.5.dp, color = NewsTheme.colors.primary)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = "Категории", style = NewsTheme.typography.displayLarge.copy(color = NewsTheme.colors.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                for(event in state.value.listCategory){
                    val isSelected = state.value.selectedCategory == event
                    val backgroundColor = if (isSelected) Color(Blue.value) else Color(Blue20.value)
                    val textColor = if (isSelected) Color(White.value) else Color(Blue.value)
                    Text(modifier = Modifier
                        .background(backgroundColor, RoundedCornerShape(15.dp))
                        .padding(vertical = 14.dp, horizontal = 20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            viewModel.stateValue = state.value.copy(selectedCategory = event)
                        }, text = event, fontSize = 12.sp,
                        fontFamily = Raleway, fontWeight = FontWeight.SemiBold,
                        color = textColor)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Пройденные мероприятия", style = NewsTheme.typography.displayLarge.copy(color = NewsTheme.colors.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(items.value) { event ->
                    EventItem(event, viewModel)
                }
            }


        }
    }
}

@Composable
fun EventItem(event: EventModel, viewModel: HomeViewModel) {

    val title = remember { MutableStateFlow("") }
    val desc = remember { MutableStateFlow("") }

    LaunchedEffect(event) { // Запускаем coroutine для обновления значений
        desc.value = when {
            event.specifications.formOfEvent != "" -> event.specifications.formOfEvent!!
            event.specifications.place != "" -> event.specifications.place!!
            event.specifications.quantityOfHours != 0 -> "Количество часов: ${event.specifications.quantityOfHours}"
            event.specifications.location != "" -> event.specifications.location!!
            else -> ""
        }
        title.value = (event.specifications.name.toString().takeIf { it.isNotBlank() } ?: event.specifications.location)!!
    }

    var showDialog by remember { mutableStateOf(false) }
    /*var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    desc = when {
        event.specifications.formOfEvent != "" -> event.specifications.formOfEvent!!
        event.specifications.place != "" -> event.specifications.place!!
        event.specifications.quantityOfHours != 0 -> "Количество часов: ${event.specifications.quantityOfHours}"
        event.specifications.location != "" -> event.specifications.location!!
        else -> ""
    }
    title = (event.specifications.name.toString().takeIf { it.isNotBlank() } ?: event.specifications.location)!!*/
    Column (modifier = Modifier
        .shadow(elevation = 4.dp, shape = RoundedCornerShape(15), spotColor = Color(Black.value))
        .clickable { showDialog = true }
        .background( color = NewsTheme.colors.primaryContainer, shape = RoundedCornerShape(15) )
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
                Text(text = title.collectAsState().value.firstCharUp(),
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
        Text(text = desc.collectAsState().value.firstCharUp(), style = NewsTheme.typography.titleMedium.copy(color = NewsTheme.colors.onPrimary),)
    }
    Spacer(modifier = Modifier.height(14.dp))
    if (showDialog) {
        ShowFragment(title.collectAsState().value, event, getColorIcon(event.formOfWork!!.name), viewModel){
            showDialog = false
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
                            style = NewsTheme.typography.titleMedium)
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
                            event.id?.let {
                                viewModel.deleteEvent(it, onDismissRequest)
                            }
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

fun sortedEvents(list: MutableList<EventModel>, idSorting: Int): MutableList<EventModel> {
    when(idSorting) {
        0 -> {
            return list.sortedWith(Comparator.comparing<EventModel, LocalDate> { model ->
                LocalDate.parse(model.dateOfEvent, DateTimeFormatter.ISO_LOCAL_DATE)
            }.reversed()) as MutableList<EventModel>
        }
        1 -> {
            return list.sortedWith(Comparator.comparing { model ->
                LocalDate.parse(model.dateOfEvent, DateTimeFormatter.ISO_LOCAL_DATE)
            }) as MutableList<EventModel>
        }
        2 -> {

        }
        3 -> {

        }
    }
    return list
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

