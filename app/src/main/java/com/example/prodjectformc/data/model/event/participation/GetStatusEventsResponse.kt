package com.example.prodjectformc.data.model.event.participation

data class GetStatusEventsResponse(
    var listStatus: List<String>? = ArrayList(),
    var error: String? = null
)
