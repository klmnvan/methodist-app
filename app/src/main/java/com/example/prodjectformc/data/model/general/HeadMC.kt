package com.example.prodjectformc.data.model.general

import kotlinx.serialization.Serializable

@Serializable
data class HeadMC(
    val fullName: String,
    val headOfCorpuseId: String?,
    val id: String,
    val idMC: String,
    val name: String,
    val patronymic: String,
    val surname: String
)