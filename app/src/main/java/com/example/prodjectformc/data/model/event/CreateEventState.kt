package com.example.prodjectformc.data.model.event

data class CreateEventState (
    var listFormOfWork: List<FormOfWork> = ArrayList(),
    var selectedFormOfWork: FormOfWork = FormOfWork()
)