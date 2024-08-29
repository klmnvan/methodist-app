package com.example.prodjectformc.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.event.EventModel
import com.example.prodjectformc.data.model.event.EventModelResponse
import com.example.prodjectformc.data.model.event.Specifications
import com.example.prodjectformc.data.model.home.HomeState
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: HomeState get() = _state.value

    fun updateState(newState: HomeState) {
        _state.value = newState
    }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    init {
        viewModelScope.launch {
            CurrentUser.accountInfo = service.getAccountInfo(CurrentUser.token)
            Log.d("accountInfo", CurrentUser.accountInfo.toString())
            if(CurrentUser.accountInfo != null){
                val listEvents = service.getEvent((CurrentUser.accountInfo!!.id), CurrentUser.token)?.toMutableList()
                Log.d("listEventsfirst", state.listEvents.toString())
                if(listEvents != null){
                    listEvents.map { it.copy(specifications =
                    it.specifications!!.replace("\\\\u[0-9a-fA-F]{4}".toRegex()) { matchResult ->
                        matchResult.value.substring(2).toInt(16).toChar().toString()
                    }) } as MutableList<EventModelResponse>
                    state.listEvents?.clear()
                    listEvents.forEach {
                        val json = Json { ignoreUnknownKeys = true } // Игнорирование неизвестных ключей
                        val sp: Specifications = json.decodeFromString(it.specifications!!)
                        state.listEvents!!.add(
                            EventModel(
                            coefficient = it.coefficient,
                            dateOfEvent = it.dateOfEvent,
                            employeeId = it.employeeId,
                            endDateOfEvent = it.endDateOfEvent,
                            formOfWork = it.formOfWork,
                            formOfWorkId = it.formOfWorkId,
                            id = it.id,
                            isApproved = it.isApproved,
                            onCheck = it.onCheck,
                            specifications = sp,
                            student = it.student,
                        )
                        )
                    }
                }
                Log.d("listEvents", state.listEvents.toString())
            }
        }
    }

    fun deleteEvent(id: String) {
        viewModelScope.launch {
            val response = service.deleteEvent(CurrentUser.token, id)
            Log.d("response delete event", response)
            if(response == "") {
                Log.d("", response)
                state.listEvents!!.removeIf { it.id == id }
                updateState(state.copy(listEvents = state.listEvents))
            } else {
                Toast.makeText(context, "$response", Toast.LENGTH_LONG).show()
            }
        }
    }
}