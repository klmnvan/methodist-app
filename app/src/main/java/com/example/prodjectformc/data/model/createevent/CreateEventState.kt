package com.example.prodjectformc.data.model.createevent

import com.example.prodjectformc.data.model.general.FormOfWork

data class CreateEventState (
    var listFormOfWork: List<FormOfWork> = ArrayList(),
    var selectedFormOfWork: FormOfWork = FormOfWork()
)