package com.example.prodjectformc.ui.screen.signup

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.prodjectformc.data.model.signin.SignInState
import com.example.prodjectformc.data.model.signup.SignUpState
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    var state by mutableStateOf(SignUpState())

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun signUp(navController: NavController) {
        viewModelScope.launch {
            val response = service.signUp(state.email, state.surname, state.name,
                state.patronymic, state.password, state.passwordConfirm)
            if(response.token != null){
                navController.navigate(RoutesNavigation.LOGIN){
                    popUpTo(RoutesNavigation.SIGNUP) {
                        inclusive = true
                    }
                }

            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

}