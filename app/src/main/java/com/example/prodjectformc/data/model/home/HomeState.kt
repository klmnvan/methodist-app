package com.example.prodjectformc.data.model.home

import com.example.prodjectformc.data.model.event.EventModel

data class HomeState(
    var searchText: String = "",
    var listEvents: MutableList<EventModel> = ArrayList(),
    var sortedType: Int = 0,
    var listSortedType: List<String> = listOf("дата: по убыванию", "дата: по возрастанию", "название: от А до Я", "название: от Я до А")

)
