package com.example.prodjectformc.ui.screen.createevent.internship

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.ui.screen.createevent.holding.HoldingViewModel

@Composable
fun Internship(navHostController: NavHostController, viewModel: InternshipViewModel = hiltViewModel()){
    Text(text = "Стажировка")
}