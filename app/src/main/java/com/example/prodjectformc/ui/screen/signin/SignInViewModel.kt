package com.example.prodjectformc.ui.screen.signin

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.signin.SignInState
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    var state by mutableStateOf(SignInState())

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun signIn(navController: NavController) {
        viewModelScope.launch {
            val response = service.signIn(state.email, state.password)
            if(response.token != null){
                CurrentUser.token = response.token
                Log.d("token", CurrentUser.token)
                navController.navigate(RoutesNavigation.GRAPH_HOME)
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

}