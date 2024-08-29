package com.example.prodjectformc.data.model.event.participation

data class GetResultEventsResponse(
    var listResult: List<String>? = ArrayList(),
    var error: String? = null
)
