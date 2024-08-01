package com.example.prodjectformc.ui.screen.home

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.R
import com.example.prodjectformc.ui.theme.Blue
import com.example.prodjectformc.ui.theme.Gray1
import com.example.prodjectformc.ui.theme.Gray2
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme
import com.example.prodjectformc.ui.theme.White
import com.example.prodjectformc.ui.theme.WhiteHomeBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController?, viewModel: HomeViewModel? = hiltViewModel()){
    val state = viewModel!!.state
    //viewModel.context = LocalContext.current
//fun Home(navHostController: NavHostController?){
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color(WhiteHomeBack.value))
        .verticalScroll(rememberScrollState())) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
            ) {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(15))
                    .background(color = Color.White, shape = RoundedCornerShape(15))
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
    }
}



/*Для удобного Preview*//*

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview(){
    ProdjectForMCTheme {
        Home(null, null)
    }
}*/
