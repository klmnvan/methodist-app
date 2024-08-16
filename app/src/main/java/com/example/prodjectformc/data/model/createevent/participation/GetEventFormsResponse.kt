package com.example.prodjectformc.data.model.createevent.participation

data class GetEventFormsResponse(
    var listFormOfEvent: List<String>? = ArrayList(),
    var error: String? = null
)
