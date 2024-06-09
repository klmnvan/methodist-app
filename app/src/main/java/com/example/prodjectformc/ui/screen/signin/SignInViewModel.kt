package com.example.prodjectformc.ui.screen.signin

import android.content.res.Configuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prodjectformc.objects.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    fun launch(navController: NavHostController, configuration: Configuration) {
        viewModelScope.launch {

        }
    }
}