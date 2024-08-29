package com.example.prodjectformc.data.model.event.publication

import kotlinx.serialization.Serializable

@Serializable
data class CreatePublicationEventRequest(
    val dateOfEvent: String? = "",
    val endDateOfEvent: String? = "",
    val name: String? = "",
    val place: String? = "",
    val type: String? = "",
)