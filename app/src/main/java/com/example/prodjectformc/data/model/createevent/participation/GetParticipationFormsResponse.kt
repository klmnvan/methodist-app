package com.example.prodjectformc.data.model.createevent.participation

data class GetParticipationFormsResponse(
    var listParticipationForms: List<String>? = ArrayList(),
    var error: String? = null
)