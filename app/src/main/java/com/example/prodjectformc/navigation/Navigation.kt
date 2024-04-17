package com.example.prodjectformc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prodjectformc.objects.RoutesNavigation
import com.example.prodjectformc.screens.LogIn
import com.example.prodjectformc.screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RoutesNavigation.SPLASH){

        composable(RoutesNavigation.SPLASH){
            SplashScreen(navController)
        }
        composable(RoutesNavigation.ONBOARD){
            //OnBoard(navController)
        }
        composable(RoutesNavigation.LOGIN){
            LogIn(navController)
        }
        composable(RoutesNavigation.SIGNUP){
            //SignUp(navController)
        }
        composable(RoutesNavigation.HOLDER){
            //Holder(navController)
        }
        composable(RoutesNavigation.FORGOTPASS){
            //ForgotPassword(navController)
        }
        composable(RoutesNavigation.OTPVERIFIC){
            //OtpVerification(navController)
        }
        composable(RoutesNavigation.NEWPASS){
            //NewPassword(navController)
        }

    }
}