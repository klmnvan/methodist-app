package com.example.prodjectformc.api

interface PostsApi {

    companion object {
        private const val URL_POODLE = "https://iis.ngknn.ru/NGKNN/МамшеваЮС/MedicMadlab"
        const val SENDCODE = "${URL_POODLE}/api/SendCode"
    }

    suspend fun sendCode(userEmail: String): String?
}