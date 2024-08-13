package com.example.prodjectformc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prodjectformc.ui.screen.createevent.CreateEvent
import com.example.prodjectformc.ui.screen.home.Home
import com.example.prodjectformc.ui.screen.profile.Profile

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = DestinationsBottomBar.CreateEventScreen.route, route = RoutesNavigation.GRAPH_HOME) {
        composable(DestinationsBottomBar.HomeScreen.route) {
            Home(navController)
        }
        composable(DestinationsBottomBar.ProfileScreen.route) {
            Profile(navController)
        }
        composable(DestinationsBottomBar.CreateEventScreen.route) {
            CreateEvent(navController)
        }
    }
}