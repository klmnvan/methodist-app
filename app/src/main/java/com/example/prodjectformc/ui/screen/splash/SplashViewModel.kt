package com.example.prodjectformc.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.DialogNavigator
import com.example.prodjectformc.data.repository.PrefManager
import com.example.prodjectformc.ui.navigation.DestinationsBottomBar
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun launch(navController: NavHostController, configuration: Configuration) {
        viewModelScope.launch {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                delay(1500L)
                if (PrefManager.act == 0){
                    navController.navigate(RoutesNavigation.LOGIN) {
                        popUpTo(RoutesNavigation.SPLASH) {
                            inclusive = true
                        }
                    }
                }
                if (PrefManager.act == 1){
                    navController.navigate(DestinationsBottomBar.HomeScreen.route) {
                        popUpTo(RoutesNavigation.SPLASH) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}