package com.example.prodjectformc.data.model.createevent.internship

import kotlinx.serialization.Serializable

@Serializable
data class CreateInternshipEventRequest(
    val dateOfEvent: String = "",
    val endDateOfEvent: String= "",
    val location: String= "",
    val quantityOfHours: Int= 0,
)