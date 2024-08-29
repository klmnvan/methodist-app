package com.example.prodjectformc.data.model.event.participation

import kotlinx.serialization.Serializable

@Serializable
data class CreateParticipationEventRequest(
    val dateOfEvent: String = "",
    val endDateOfEvent: String = "",
    val formOfEvent: String = "",
    val formOfParticipation: String = "",
    val name: String = "",
    val result: String = "",
    val status: String = ""
)