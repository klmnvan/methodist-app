package com.example.prodjectformc.data.model.home

import com.example.prodjectformc.data.model.general.Event
import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.data.model.general.FormOfWork
import java.util.LinkedList

data class HomeState(
    var searchText: String = "тест",

    var listEvent: MutableList<EventModel> = LinkedList(listOf(
        EventModel(),
        EventModel(),
        EventModel(),
        EventModel(),
        EventModel(),
    )),


)
