package com.example.prodjectformc.data.model.signin

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val email: String?,
    val password: String?,
)
