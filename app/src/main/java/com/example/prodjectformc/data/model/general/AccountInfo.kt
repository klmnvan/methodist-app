package com.example.prodjectformc.data.model.general

import kotlinx.serialization.Serializable

@Serializable
data class AccountInfo(
    val email: String = "",
    val headOfCorpuse: HeadOfCorpuse? = HeadOfCorpuse(),
    val id: String = "",
    val methodicalCommision: MethodicalCommision = MethodicalCommision(),
    val name: String = "",
    val patronymic: String = "",
    val role: List<String> = ArrayList(),
    val surname: String = "",
)