package com.example.prodjectformc.ui.screen.createevent.holding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun Holding(navHostController: NavHostController, viewModel: HoldingViewModel = hiltViewModel()){
    Text(text = "Проведение")
}