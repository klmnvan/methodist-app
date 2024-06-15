package com.example.prodjectformc.data.model.signup

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val email: String,
    val name: String,
    val password: String,
    val passwordConfirm: String,
    val patronymic: String,
    val surname: String
)