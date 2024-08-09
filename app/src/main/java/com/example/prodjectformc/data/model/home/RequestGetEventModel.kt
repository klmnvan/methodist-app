package com.example.prodjectformc.data.model.home

import kotlinx.serialization.Serializable

@Serializable
data class RequestGetEventModel(
    val DateEventFrom: String? = "",
    val DateEventTo: String? = "",
    val ForCheck: Boolean? = false,
    val FormOfWork: String? = "",
    val IdEmploeyy: String? = "",
    val InCheck: Boolean? = false,
    val IsApproved: Boolean? = false,
    val MethodicalCommisiom: String? = "",
    val ReportingPeriod: String? = ""
)