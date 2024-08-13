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
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    val state by mutableStateOf(CreateEventState())

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    init {
        viewModelScope.launch {
            val response = service.getFormOfWorks(CurrentUser.token)
            if(response.listParticipationForms != null){
                state.listFormOfWork = response.listParticipationForms!!
                state.selectedFormOfWork = state.listFormOfWork[0]
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
                navController.navigate(RoutesNavigation.PUBLICATION)
            }
            "Участие" -> {
                navController.navigate(RoutesNavigation.PARTICIPATION)
            }
            "Стажировка" -> {
                navController.navigate(RoutesNavigation.INTERNSHIP)
            }
            "Проведение" -> {
                navController.navigate(RoutesNavigation.HOLDING)
            }
        }
    }

}