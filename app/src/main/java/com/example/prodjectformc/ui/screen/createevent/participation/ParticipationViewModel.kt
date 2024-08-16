package com.example.prodjectformc.ui.screen.createevent.participation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.DialogNavigator
import com.example.prodjectformc.data.model.createevent.participation.CreateParticipationEventRequest
import com.example.prodjectformc.data.model.createevent.participation.ParticipationState
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.DestinationsBottomBar
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import com.example.prodjectformc.ui.screen.createevent.CreateEventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParticipationViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = mutableStateOf(ParticipationState())
    val state: ParticipationState get() = _state.value

    fun updateState(newState: ParticipationState) {
        _state.value = newState
    }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    init {
        viewModelScope.launch {
            val response = service.getParticipationForms(CurrentUser.token)
            if(response.listParticipationForms != null){
                state.listFormOfParticipation = response.listParticipationForms!!
                state.formOfParticipation = state.listFormOfParticipation[0]
                Log.d("listParticipationForms", state.listFormOfParticipation.toString())
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
        viewModelScope.launch {
            val response = service.getEventForms(CurrentUser.token)
            if(response.listFormOfEvent != null){
                state.listFormOfEvent = response.listFormOfEvent!!
                state.formOfEvent = state.listFormOfEvent[0]
                Log.d("listFormOfEvent", state.listFormOfEvent.toString())
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
        viewModelScope.launch {
            val response = service.getResultEvents(CurrentUser.token)
            if(response.listResult != null){
                state.listResult = response.listResult!!
                state.result = state.listResult[0]
                Log.d("listResult", state.listResult.toString())
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
        viewModelScope.launch {
            val response = service.getStatusEvents(CurrentUser.token)
            if(response.listStatus != null){
                state.listStatus = response.listStatus!!
                state.status = state.listStatus[0]
                Log.d("listStatus", state.listStatus.toString())
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun CreateParticipationEvent(navController: NavHostController){
        viewModelScope.launch {
            val response = service.createParticipationEvent(CurrentUser.token,
                CreateParticipationEventRequest(
                    name = state.name,
                    result = state.result,
                    formOfEvent = state.formOfEvent,
                    status = state.status,
                    dateOfEvent = state.dateOfEvent,
                    endDateOfEvent = state.endDateOfEvent,
                    formOfParticipation = state.formOfParticipation
                )
            )
            if(response.event != null){
                Log.d("event", response.event.toString())
                navController.navigate(DestinationsBottomBar.HomeScreen.route)
            }
            if (response.error != null){
                Toast.makeText(context, "${response.error}", Toast.LENGTH_LONG).show()
            }
        }
    }

}