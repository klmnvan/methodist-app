package com.example.prodjectformc.data.model.general

import kotlinx.serialization.Serializable

@Serializable
data class Specifications(
    val Name: String,
    val FormOfEvent: String,
    val Location: String,
    val Status: String,
    val Result: String
)