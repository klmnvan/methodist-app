package com.example.prodjectformc.data.model.home

import com.example.prodjectformc.data.model.event.EventModel

data class HomeState(
    var searchText: String = "тест",
    var listEvents: MutableList<EventModel>? = ArrayList()
)
