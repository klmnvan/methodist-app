package com.example.prodjectformc.data.model.event.participation

data class GetEventFormsResponse(
    var listFormOfEvent: List<String>? = ArrayList(),
    var error: String? = null
)
