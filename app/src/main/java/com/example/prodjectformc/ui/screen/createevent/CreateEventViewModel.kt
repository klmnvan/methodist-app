package com.example.prodjectformc.ui.screen.createevent

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.prodjectformc.data.model.createevent.CreateEventState
import com.example.prodjectformc.data.model.createevent.publication.PublicationState
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.DestinationsBottomBar
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    private val _state = mutableStateOf(CreateEventState())
    val state: CreateEventState get() = _state.value

    fun updateState(newState: CreateEventState) {
        _state.value = newState
    }

    init {
        viewModelScope.launch {
            val response = service.getFormOfWorks(CurrentUser.token)
            if(response.listParticipationForms != null){
                state.listFormOfWork = response.listParticipationForms!!
                Log.d("listParticipationForms", CurrentUser.token)
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun openForm(navController: NavController) {
        CurrentUser.selectedTypeOfWork = state.selectedFormOfWork
        when(state.selectedFormOfWork.name){
            "Публикация" -> {
                navController.navigate(RoutesNavigation.PUBLICATION){
                    popUpTo(DestinationsBottomBar.CreateEventScreen.route) {
                        inclusive = true
                    }
                }
            }
            "Участие" -> {
                navController.navigate(RoutesNavigation.PARTICIPATION){
                    popUpTo(DestinationsBottomBar.CreateEventScreen.route) {
                        inclusive = true
                    }
                }
            }
            "Стажировка" -> {
                navController.navigate(RoutesNavigation.INTERNSHIP){
                    popUpTo(DestinationsBottomBar.CreateEventScreen.route) {
                        inclusive = true
                    }
                }
            }
            "Проведение" -> {
                navController.navigate(RoutesNavigation.HOLDING){
                    popUpTo(DestinationsBottomBar.CreateEventScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

}