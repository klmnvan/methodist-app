package com.example.prodjectformc.ui.screen.createevent.publication

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.data.model.general.FormOfWork
import com.example.prodjectformc.ui.screen.createevent.participation.ParticipationViewModel

@Composable
fun Publication(navHostController: NavHostController, viewModel: PublicationViewModel = hiltViewModel()){
    Text(text = "Публикация")
}