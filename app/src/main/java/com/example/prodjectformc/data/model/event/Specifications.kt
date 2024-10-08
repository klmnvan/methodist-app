package com.example.prodjectformc.data.model.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Specifications(
    @SerialName("Name")
    val name: String? = "",
    @SerialName("FormOfEvent")
    val formOfEvent: String? = "",
    @SerialName("Location")
    val location: String? = "",
    @SerialName("Status")
    val status: String? = "",
    @SerialName("FormOfParticipation")
    val formOfParticipation: String? = "",
    @SerialName("Result")
    val result: String? = "",
    @SerialName("QuantityOfHours")
    val quantityOfHours: Int? = null,
    @SerialName("DateOfEvent")
    val dateOfEvent: String? = "",
    @SerialName("EndDateOfEvent")
    val endDateOfEvent: String? = "",
    @SerialName("Place")
    val place: String? = "",
    @SerialName("Type")
    val type: String? = "",
)