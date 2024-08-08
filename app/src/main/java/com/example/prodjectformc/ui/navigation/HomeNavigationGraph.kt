package com.example.prodjectformc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prodjectformc.ui.screen.Holder
import com.example.prodjectformc.ui.screen.home.Home

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route, route = RoutesNavigation.GRAPHHOME) {
        composable(Destinations.HomeScreen.route) {
            Home(navController)
        }
        composable(Destinations.Profile.route) {
            Holder(navController)
        }
    }
}