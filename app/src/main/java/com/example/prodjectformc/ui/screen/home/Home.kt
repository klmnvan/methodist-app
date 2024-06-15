package com.example.prodjectformc.ui.screen.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.ui.theme.ProdjectForMCTheme


@Composable
fun Home(navHostController: NavHostController?, viewModel: HomeViewModel = hiltViewModel()){
    Text(text = "Home")
}

/*Для удобного Preview*/
@Preview(showBackground = true)
@Composable
private fun Preview(){
    ProdjectForMCTheme {
        Home(null)
    }
}