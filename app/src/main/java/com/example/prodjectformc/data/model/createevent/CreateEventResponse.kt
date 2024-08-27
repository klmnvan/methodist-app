package com.example.prodjectformc.data.model.createevent

import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.data.model.general.EventModelResponse
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventResponse(
    var event: EventModelResponse? = null,
    var error: String? = null
)
