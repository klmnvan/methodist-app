package com.example.prodjectformc.ui.screen.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.prodjectformc.data.model.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    //здесь надо будет передать преференс и апи сервис
) : ViewModel() {

    var state by mutableStateOf(SignInState())

    fun signIn(navController: NavController) {
        viewModelScope.launch {
           //тут будет отправка запроса к апишке
        }
    }

}