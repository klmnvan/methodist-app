package com.example.prodjectformc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prodjectformc.ui.screen.createevent.CreateEvent
import com.example.prodjectformc.ui.screen.createevent.holding.Holding
import com.example.prodjectformc.ui.screen.createevent.internship.Internship
import com.example.prodjectformc.ui.screen.createevent.participation.Participation
import com.example.prodjectformc.ui.screen.createevent.publication.Publication
import com.example.prodjectformc.ui.screen.home.Home
import com.example.prodjectformc.ui.screen.profile.Profile
import com.example.prodjectformc.ui.screen.signin.SignIn
import com.example.prodjectformc.ui.screen.signup.SignUp
import com.example.prodjectformc.ui.screen.splash.SplashScreen
import com.example.prodjectformc.ui.theme.custom.ThemeMode

@Composable
fun RootNavigationGraph(navController: NavHostController, visibleBBar: MutableState<Boolean>, currentThemeMode: MutableState<ThemeMode>) {
    val homeNavController = rememberNavController()
    NavHost(
        route = RoutesNavigation.GRAPH_ROOT,
        navController = navController,
        startDestination = RoutesNavigation.SPLASH){

        composable(RoutesNavigation.SPLASH){
            SplashScreen(navController)
        }
        composable(RoutesNavigation.LOGIN){
            SignIn(navController)
            visibleBBar.value = false
        }
        composable(RoutesNavigation.HOME){
            Home(navController)
        }
        composable(RoutesNavigation.SIGNUP){
            SignUp(navController)
        }
        composable(RoutesNavigation.HOLDER){
            //Holder(navController)
        }
        /*composable(RoutesNavigation.GRAPH_HOME){
            MainScaffold(homeNavController, navController)
        }*/
        composable(DestinationsBottomBar.HomeScreen.route) {
            Home(navController)
            visibleBBar.value = true
        }
        composable(DestinationsBottomBar.ProfileScreen.route) {
            Profile(navController, currentThemeMode)
        }
        composable(DestinationsBottomBar.CreateEventScreen.route) {
            CreateEvent(navController)
        }
        composable(RoutesNavigation.PARTICIPATION){
            Participation(navHostController = navController)
        }
        composable(RoutesNavigation.INTERNSHIP){
            Internship(navHostController = navController)
        }
        composable(RoutesNavigation.PUBLICATION){
            Publication(navHostController = navController)
        }
        composable(RoutesNavigation.HOLDING){
            Holding(navHostController = navController)
        }
    }
}