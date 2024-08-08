package com.example.prodjectformc.ui.screen.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.prodjectformc.ui.navigation.HomeNavigationGraph
import com.example.prodjectformc.ui.screen.bottombar.BottomBar

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
            HomeNavigationGraph(navController = navController)
        }
    }
}