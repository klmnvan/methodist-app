package com.example.prodjectformc.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.prodjectformc.ui.navigation.NavigationGraph

@Composable
fun HomeBottomBar(navController: NavHostController){
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                modifier = Modifier
            )
        }) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            NavigationGraph(navController = navController)
        }
    }
}