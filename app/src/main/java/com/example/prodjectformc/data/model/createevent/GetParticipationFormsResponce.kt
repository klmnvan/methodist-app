package com.example.prodjectformc.data.model.createevent

import com.example.prodjectformc.data.model.general.FormOfWork

data class GetParticipationFormsResponce(
    var listParticipationForms: List<String>? = ArrayList(),
    var error: String? = null
)