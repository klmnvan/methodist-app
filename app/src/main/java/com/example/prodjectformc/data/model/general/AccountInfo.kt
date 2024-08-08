package com.example.prodjectformc.data.model.general

import kotlinx.serialization.Serializable

@Serializable
data class AccountInfo(
    val email: String,
    val headOfCorpuse: HeadOfCorpuse?,
    val id: String,
    val methodicalCommision: MethodicalCommision,
    val name: String,
    val patronymic: String,
    val surname: String
)