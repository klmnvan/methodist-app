package com.example.prodjectformc.data.model.general

import kotlinx.serialization.Serializable

@Serializable
data class EventModelResponse(
    val coefficient: Int? = 0,
    val dateOfEvent: String = "",
    val employeeId: String? = "",
    val endDateOfEvent: String? = "",
    val formOfWork: FormOfWork? = FormOfWork(),
    val formOfWorkId: String? = "",
    val id: String? = "",
    val isApproved: Boolean? = false,
    val onCheck: Boolean? = false,
    var specifications: String? = "",
    val student: String? = ""
)