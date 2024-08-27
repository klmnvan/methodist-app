package com.example.prodjectformc.data.model.auth.signin

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val email: String?,
    val password: String?,
)
