package com.example.prodjectformc.data.model.event

import kotlinx.serialization.Serializable

@Serializable
data class FormOfWork(
    val id: String = "",
    val name: String = ""
)