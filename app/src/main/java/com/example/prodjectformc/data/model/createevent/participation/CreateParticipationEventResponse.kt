package com.example.prodjectformc.data.model.createevent.participation

import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.data.model.general.FormOfWork
import kotlinx.serialization.Serializable

@Serializable
data class CreateParticipationEventResponse(
    var event: EventModel? = null,
    var error: String? = null
)
