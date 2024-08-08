package com.example.prodjectformc.data.network

object HttpRoutes {
    private const val BASE_URL = "https://iis.ngknn.ru/NGKNN/ТаранАА/Api"
    const val LOGIN = "$BASE_URL/Account/Login"
    const val REGISTER = "$BASE_URL/Account/Register"
    const val INFORMATION = "$BASE_URL/Account/Infortmation"
}