package com.example.prodjectformc.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.theme.Black
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Green
import com.example.prodjectformc.ui.theme.White
import com.example.prodjectformc.ui.theme.WhiteHomeBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController?, viewModel: HomeViewModel = hiltViewModel()){
    val state = viewModel.state
    val scrollState = rememberScrollState()
    //viewModel.context = LocalContext.current
//fun Home(navHostController: NavHostController?){
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
                Box(modifier = Modifier.fillMaxWidth(0.985f)
                    .shadow(
                        elevation = 5.dp, shape = RoundedCornerShape(30), spotColor = Color(
                            Black.value), ambientColor = Color(Black.value))
                    .background(color = Color.Transparent, shape = RoundedCornerShape(30))
                    .padding(horizontal = 5.dp).height(40.dp).align(Alignment.BottomCenter)
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
            Spacer(modifier = Modifier.height(17.dp))
            Text(text = "Пройденные мероприятия", style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(17.dp))
            Row(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp, shape = RoundedCornerShape(30), spotColor = Color(
                            Black.value
                        )
                    )
                    .background(color = Color.White, shape = RoundedCornerShape(30))
                    .padding(14.dp)
                    .clickable {

                    }
            )
            {
                Text(text = "Сортировать по", style = MaterialTheme.typography.titleMedium,)
                Spacer(modifier = Modifier.width(60.dp))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_right),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(vertical = 4.dp)
                        .fillMaxWidth(),
                    tint = Color(Blue.value)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            for (event in state.listEvent) {
                Column (modifier = Modifier
                    .shadow(
                        elevation = 4.dp, shape = RoundedCornerShape(15), spotColor = Color(
                            Black.value
                        )
                    )
                    .background(color = Color(White.value), shape = RoundedCornerShape(15))
                    .padding(vertical = 10.dp, horizontal = 18.dp)) {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_event),
                            contentDescription = "",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(vertical = 4.dp)
                                .fillMaxWidth(),
                            tint = Color(Green.value)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = event.title,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleMedium,)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = event.date, style = MaterialTheme.typography.titleMedium)
                    }
                    Divider(modifier = Modifier.padding(vertical = 10.dp))
                    Text(text = event.description, style = MaterialTheme.typography.titleMedium,)
                }
                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}



//Для удобного Preview

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview(){
    ProdjectForMCTheme {
        Home(null)
    }
}*/
