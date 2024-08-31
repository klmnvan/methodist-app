package com.example.prodjectformc.ui.screen.createevent.internship

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prodjectformc.data.model.event.internship.CreateInternshipEventRequest
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.DestinationsBottomBar
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternshipViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = mutableStateOf(CreateInternshipEventRequest())
    val state: CreateInternshipEventRequest get() = _state.value

    fun updateState(newState: CreateInternshipEventRequest) {
        _state.value = newState
    }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun createEvent(navController: NavHostController){
        viewModelScope.launch {
            val response = service.createInternshipEvent(
                CurrentUser.token,
                state
            )
            if(response.event != null){
                Log.d("event", response.event.toString())
                navController.navigate(DestinationsBottomBar.HomeScreen.route){
                    popUpTo(RoutesNavigation.INTERNSHIP) {
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