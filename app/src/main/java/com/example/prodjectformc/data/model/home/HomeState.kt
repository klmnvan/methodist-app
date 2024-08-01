package com.example.prodjectformc.data.model.home

import com.example.prodjectformc.data.model.general.Event
import java.util.LinkedList

data class HomeState(
    var searchText: String = "тест",

    var listEvent: MutableList<Event> = LinkedList(listOf(
        Event(1, "Заголовок 1","01.01.2024","Описание1"),
        Event(2, "Заголовок 2","01.02.2024","Описание2"),
        Event(2, "Заголовок 2","01.02.2024","Описание2"),
        Event(2, "Заголовок 2","01.02.2024","Описание2"),
        Event(2, "Заголовок 2","01.02.2024","Описание2"),
        Event(2, "Заголовок 2","01.02.2024","Описание2"),
        Event(2, "Заголовок 2","01.02.2024","Описание2"),
        Event(3, "Заголовок 3","01.03.2024","Описание3")
    ))


)
