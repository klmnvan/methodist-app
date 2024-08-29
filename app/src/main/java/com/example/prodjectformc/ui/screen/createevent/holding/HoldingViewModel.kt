package com.example.prodjectformc.ui.screen.createevent.holding

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.prodjectformc.data.model.event.holding.CreateHoldingEventRequest
import com.example.prodjectformc.data.model.event.holding.HoldingState
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.network.ApiServiceImpl
import com.example.prodjectformc.ui.navigation.RoutesNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = mutableStateOf(HoldingState())
    val state: HoldingState get() = _state.value

    fun updateState(newState: HoldingState) {
        _state.value = newState
    }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    init {
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

    fun createEvent(navController: NavHostController){
        viewModelScope.launch {
            val response = service.createHoldingEvent(CurrentUser.token,
                CreateHoldingEventRequest(
                    dateOfEvent = state.dateOfEvent,
                    endDateOfEvent = state.dateOfEvent,
                    formOfEvent = state.formOfEvent,
                    location = state.location,
                    name = state.name,
                    result = state.result,
                    status = state.status,
                )
            )
            if(response.event != null){
                Log.d("event", response.event.toString())
                navController.navigate(RoutesNavigation.GRAPH_HOME){
                    popUpTo(RoutesNavigation.HOLDING) {
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