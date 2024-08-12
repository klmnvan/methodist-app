package com.example.prodjectformc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prodjectformc.ui.screen.bottombar.MainScaffold
import com.example.prodjectformc.ui.screen.home.Home
import com.example.prodjectformc.ui.screen.signin.SignIn
import com.example.prodjectformc.ui.screen.signup.SignUp
import com.example.prodjectformc.ui.screen.splash.SplashScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    val homeNavController = rememberNavController()
    NavHost(
        route = RoutesNavigation.GRAPH_ROOT,
        navController = navController,
        startDestination = RoutesNavigation.GRAPH_HOME){

        composable(RoutesNavigation.SPLASH){
            SplashScreen(navController)
        }
        composable(RoutesNavigation.LOGIN){
            SignIn(navController)
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
        composable(RoutesNavigation.GRAPH_HOME){
            MainScaffold(homeNavController)
        }
    }
}