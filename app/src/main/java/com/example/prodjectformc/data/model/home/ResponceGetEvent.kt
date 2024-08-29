package com.example.prodjectformc.data.model.home

import com.example.prodjectformc.data.model.event.FormOfWork
import kotlinx.serialization.Serializable

@Serializable
data class ResponceGetEvent(
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