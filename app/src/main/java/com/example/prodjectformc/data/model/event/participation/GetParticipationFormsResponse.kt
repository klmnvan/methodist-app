package com.example.prodjectformc.data.model.event.participation

data class GetParticipationFormsResponse(
    var listParticipationForms: List<String>? = ArrayList(),
    var error: String? = null
)