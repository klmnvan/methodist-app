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
import com.example.prodjectformc.data.model.auth.signin.SignInState
import com.example.prodjectformc.data.model.profile.ProfileState
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.data.repository.PrefManager
import com.example.prodjectformc.ui.navigation.DestinationsBottomBar
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> get() = _state.asStateFlow()

    var stateValue: SignInState
        get() = _state.value
        set(value) {
            _state.value = value
        }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun signIn(navController: NavController) {
        viewModelScope.launch {
            Log.d("test", "${state.value.email} ${state.value.password}")
            val response = service.signIn(state.value.email, state.value.password)
            if(response.token != null){
                PrefManager.act = 1
                PrefManager.token = response.token
                CurrentUser.token = response.token
                Log.d("token", CurrentUser.token)
                navController.navigate(DestinationsBottomBar.HomeScreen.route){
                    popUpTo(RoutesNavigation.LOGIN) {
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