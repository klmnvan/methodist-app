package com.example.prodjectformc.ui.screen.home

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.data.model.general.CurrentUser
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
                        if (state.searchText.isEmpty()) {
                            Text(text = "Поиск", style = MaterialTheme.typography.labelMedium)
                        }
                        BasicTextField(
                            value = state.searchText,
                            onValueChange = { viewModel.state = viewModel.state.copy(searchText = it) },
                            modifier = Modifier.weight(1f),
                            textStyle = MaterialTheme.typography.titleMedium,
                            singleLine = true,
                            maxLines = 1
                        )
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
                    Column (modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(15),
                            spotColor = Color(Black.value)
                        )
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
                            Text(text = event.dateOfEvent,
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
                }
            }
        }
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

