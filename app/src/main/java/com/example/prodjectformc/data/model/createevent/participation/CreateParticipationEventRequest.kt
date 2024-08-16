package com.example.prodjectformc.data.model.createevent.participation

data class CreateParticipationEventRequest(
    val dateOfEvent: String,
    val endDateOfEvent: String,
    val formOfEvent: String,
    val formOfParticipation: String,
    val name: String,
    val result: String,
    val status: String
)