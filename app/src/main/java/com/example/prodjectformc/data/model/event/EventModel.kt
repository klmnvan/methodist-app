package com.example.prodjectformc.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class EventModel(
    val coefficient: Int? = 0,
    val dateOfEvent: String = "",
    val employeeId: String? = "",
    val endDateOfEvent: String? = "",
    val formOfWork: FormOfWork? = FormOfWork(),
    val formOfWorkId: String? = "",
    val id: String? = "",
    val isApproved: Boolean? = false,
    val onCheck: Boolean? = false,
    var specifications: Specifications = Specifications(),
    val student: String? = ""
)