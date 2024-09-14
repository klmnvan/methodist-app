package com.example.prodjectformc.ui.screen.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.event.EventModel
import com.example.prodjectformc.data.model.event.EventModelResponse
import com.example.prodjectformc.data.model.event.Specifications
import com.example.prodjectformc.data.model.home.HomeState
import com.example.prodjectformc.data.network.ApiServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val service: ApiServiceImpl
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state.asStateFlow()

    var stateValue: HomeState
        get() = _state.value
        set(value) {
            _state.value = value
        }

    private val _filteredListEvent = MutableStateFlow(mutableListOf<EventModel>())
    val filteredListEvent: StateFlow<MutableList<EventModel>> = _filteredListEvent.asStateFlow()

    fun filteredListEvents() {
        val pattern = Regex(state.value.searchText, RegexOption.IGNORE_CASE)
        val filteredList = state.value.listEvents.filter { if(state.value.selectedCategory != "Всё") it.formOfWork!!.name.contains(state.value.selectedCategory)
        else it.formOfWork!!.name.contains("")}.filter { pattern.containsMatchIn(it.specifications.toString()) }
        _filteredListEvent.value = filteredList.toMutableList()
    }

    /*val filteredListEventValue: MutableList<EventModel>
        get() {
            val pattern = Regex(state.value.searchText, RegexOption.IGNORE_CASE)
            val filteredList = state.value.listEvents.filter { if(state.value.selectedCategory != "Всё") it.formOfWork!!.name.contains(state.value.selectedCategory)
            else it.formOfWork!!.name.contains("")}.filter { pattern.containsMatchIn(it.specifications.toString()) }
            return filteredList.toMutableList()
        }*/

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    suspend fun launch() {
        withContext(Dispatchers.IO) {
            CurrentUser.accountInfo = service.getAccountInfo(CurrentUser.token)
            Log.d("accountInfo", CurrentUser.accountInfo.toString())
            if(CurrentUser.accountInfo != null){
                val listEvents = service.getEvent((CurrentUser.accountInfo!!.id), CurrentUser.token)?.toMutableList()
                Log.d("listEventsfirst", stateValue.listEvents.toString())
                if(listEvents != null){
                    listEvents.map { it.copy(specifications =
                    it.specifications!!.replace("\\\\u[0-9a-fA-F]{4}".toRegex()) { matchResult ->
                        matchResult.value.substring(2).toInt(16).toChar().toString()
                    }) } as MutableList<EventModelResponse>
                    stateValue.listEvents.clear()
                    listEvents.forEach {
                        val json = Json { ignoreUnknownKeys = true } // Игнорирование неизвестных ключей
                        val sp: Specifications = json.decodeFromString(it.specifications!!)
                        stateValue.listEvents.add(EventModel(
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
                            student = it.student))
                    }
                    val listNewCategory = state.value.listEvents.map { it.formOfWork!!.name }.distinct().toMutableList()
                    listNewCategory.add(0, "Всё")
                    stateValue = stateValue.copy(listCategory = listNewCategory)
                }
                Log.d("listEvents", stateValue.listEvents.toString())
            }
        }
    }

    fun deleteEvent(id: String, onDismissRequest: () -> Unit) {
        viewModelScope.launch {
            val response = service.deleteEvent(CurrentUser.token, id)
            Log.d("response delete event", response)
            if(response == "") {
                Log.d("", response)
                _filteredListEvent.value.removeIf { it.id == id }
                onDismissRequest()
            } else {
                Toast.makeText(context, "$response", Toast.LENGTH_LONG).show()
            }
        }
    }
}