package com.example.prodjectformc.data.model.event.participation

data class ParticipationState(
    var name: String = "",
    var formOfParticipation: String = "",
    var listFormOfParticipation: List<String> = ArrayList(),
    var formOfEvent: String = "",
    var listFormOfEvent: List<String> = ArrayList(),
    var status: String = "",
    var listStatus: List<String> = ArrayList(),
    var result: String = "",
    var listResult: List<String> = ArrayList(),
    var dateOfEvent: String = "",
    var endDateOfEvent: String = "",
)