package com.example.prodjectformc.data.model.createevent

import com.example.prodjectformc.data.model.general.EventModel
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventResponse(
    var event: EventModel? = null,
    var error: String? = null
)
