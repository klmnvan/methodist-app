package com.example.prodjectformc.ui.screen.splash

import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    fun launch(navController: NavHostController, configuration: Configuration) {
        viewModelScope.launch {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                delay(1500L)
                //if (PrefManager.act == 0){
                navController.navigate(RoutesNavigation.LOGIN) {
                    popUpTo(RoutesNavigation.SPLASH) {
                        inclusive = true
                    }
                }
            }
            navController.navigate(RoutesNavigation.LOGIN)
        }
    }
}