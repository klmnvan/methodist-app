package com.example.prodjectformc.data.model.createevent

import com.example.prodjectformc.data.model.general.FormOfWork

class GetFormOfWorksResponce (
    var listParticipationForms: List<FormOfWork>? = ArrayList(),
    var error: String? = null
)