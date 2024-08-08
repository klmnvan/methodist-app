package com.example.prodjectformc.data.model.general

import kotlinx.serialization.Serializable

@Serializable
data class MethodicalCommision(
    val headMC: HeadMC,
    val id: String,
    val name: String
)