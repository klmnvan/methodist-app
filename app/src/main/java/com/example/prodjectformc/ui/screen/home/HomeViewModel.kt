package com.example.prodjectformc.ui.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.data.model.general.EventModelResponse
import com.example.prodjectformc.data.model.general.Specifications
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

    var state by mutableStateOf(HomeState())

    init {
        viewModelScope.launch {
            CurrentUser.accountInfo = service.getAccountInfo(CurrentUser.token)
            Log.d("accountInfo", CurrentUser.accountInfo.toString())
            if(CurrentUser.accountInfo != null){
                val listEvents = service.getEvent((CurrentUser.accountInfo!!.id), CurrentUser.token)?.toMutableList()
                Log.d("listEventsfirst", CurrentUser.listEvents.toString())
                if(listEvents != null){
                    listEvents.map { it.copy(specifications =
                    it.specifications!!.replace("\\\\u[0-9a-fA-F]{4}".toRegex()) { matchResult ->
                        matchResult.value.substring(2).toInt(16).toChar().toString()
                    }) } as MutableList<EventModelResponse>
                    listEvents.forEach {
                        val json = Json { ignoreUnknownKeys = true } // Игнорирование неизвестных ключей
                        val sp: Specifications = json.decodeFromString(it.specifications!!)
                        CurrentUser.listEvents!!.add(EventModel(
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
                        ))
                    }
                }
                Log.d("listEvents", CurrentUser.listEvents.toString())
            }
        }
    }



}

fun decodeUnicodeString(input: String): String {
    return input.replace("\\\\u[0-9a-fA-F]{4}".toRegex()) { matchResult ->
        matchResult.value.substring(2).toInt(16).toChar().toString()
    }
}