package com.example.prodjectformc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prodjectformc.ui.screen.home.Home
import com.example.prodjectformc.ui.screen.signin.SignIn
import com.example.prodjectformc.ui.screen.signup.SignUp
import com.example.prodjectformc.ui.screen.splash.SplashScreen

@Composable
fun Navigation(
//Сюда можно передавать VM с hilt
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RoutesNavigation.HOME){

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
    }
}