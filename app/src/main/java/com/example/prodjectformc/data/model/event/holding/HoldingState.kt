package com.example.prodjectformc.data.model.event.holding

data class HoldingState(
    var dateOfEvent: String = "",
    var formOfEvent: String = "",
    var listFormOfEvent: List<String> = ArrayList(),
    var location: String = "",
    var name: String = "",
    var result: String = "",
    var listResult: List<String> = ArrayList(),
    var status: String = "",
    var listStatus: List<String> = ArrayList(),
)
