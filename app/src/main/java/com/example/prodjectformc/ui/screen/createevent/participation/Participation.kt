package com.example.prodjectformc.ui.screen.createevent.participation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prodjectformc.data.model.general.FormOfWork

@Composable
fun Participation(navHostController: NavHostController, viewModel: ParticipationViewModel = hiltViewModel()) {
    Text(text = "Участие")
}
