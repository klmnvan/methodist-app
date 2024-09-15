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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.math.log

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

    fun filteredListEvents() {
        if (stateValue.listEvents.isNotEmpty()){
            val pattern = Regex(state.value.searchText, RegexOption.IGNORE_CASE)
            val filteredList = state.value.listEvents.filter { if(state.value.selectedCategory != "Всё") it.formOfWork!!.name.contains(state.value.selectedCategory)
            else it.formOfWork!!.name.contains("")}.filter { pattern.containsMatchIn(it.specifications.toString()) }
            _state.value = stateValue.copy(filteredListEvent = sortedEvents(filteredList.toMutableList(), state.value.sortedType))
        }
    }

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    suspend fun launch() {
        viewModelScope.launch {
            Log.d("accountInfo", CurrentUser.token)
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
                    filteredListEvents()
                    stateValue = state.value.copy(filteredListEvent = sortedEvents(state.value.listEvents, state.value.sortedType))
                    Log.d("listEvents", stateValue.listEvents.toString())
                }
            }
        }
    }

    fun sortedEvents(list: MutableList<EventModel>, idSorting: Int): MutableList<EventModel> {
        if(list.isNotEmpty()){
            when(idSorting) {
                0 -> {
                    return list.sortedWith(Comparator.comparing<EventModel, LocalDate> { model ->
                        LocalDate.parse(model.dateOfEvent, DateTimeFormatter.ISO_LOCAL_DATE)
                    }.reversed()) as MutableList<EventModel>
                }
                1 -> {
                    return list.sortedWith(Comparator.comparing { model ->
                        LocalDate.parse(model.dateOfEvent, DateTimeFormatter.ISO_LOCAL_DATE)
                    }) as MutableList<EventModel>
                }
                2 -> {

                }
                3 -> {

                }
            }
        }
        return list
    }

    fun deleteEvent(id: String, onDismissRequest: () -> Unit) {
        viewModelScope.launch {
            val response = service.deleteEvent(CurrentUser.token, id)
            if(response == "") {
                Log.d("", response)
                val list = stateValue.listEvents
                list.removeIf { it.id == id }
                stateValue = stateValue.copy(listEvents = list)
                filteredListEvents()
                onDismissRequest()
            } else {
                Toast.makeText(context, "$response", Toast.LENGTH_LONG).show()
            }
        }
    }
}