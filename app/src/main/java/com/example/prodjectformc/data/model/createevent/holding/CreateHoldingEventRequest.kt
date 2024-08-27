package com.example.prodjectformc.data.model.createevent.holding

import kotlinx.serialization.Serializable

@Serializable
data class CreateHoldingEventRequest(
    val dateOfEvent: String,
    val endDateOfEvent: String,
    val formOfEvent: String,
    val location: String,
    val name: String,
    val result: String,
    val status: String
)