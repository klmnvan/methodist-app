package com.example.prodjectformc.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class CreateEventResponse(
    var event: EventModelResponse? = null,
    var error: String? = null
)
