package com.example.prodjectformc.data.model.auth.signup

data class SignUpState(
    var email: String = "",
    var surname: String = "",
    var name: String = "",
    var patronymic: String = "",
    var password: String = "",
    var passwordConfirm: String = "",
)

