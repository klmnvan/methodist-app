package com.example.prodjectformc.data.network

import com.example.prodjectformc.data.model.Response
import com.example.prodjectformc.data.model.general.AccountInfo
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ApiService {

    suspend fun signIn(email: String, password: String): Response
    suspend fun getAccountInfo(token: String): AccountInfo?
    suspend fun signUp(email: String, surname: String, name: String, patronymic: String, password: String, passwordConfirm: String): Response

    companion object {
        fun create(): ApiServiceImpl {
            return ApiServiceImpl(
                client = HttpClient(Android){
                    expectSuccess = true
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(ContentNegotiation){
                        json(
                            Json {
                            encodeDefaults = false
                            ignoreUnknownKeys = true
                            isLenient = true
                            useAlternativeNames = false
                        })
                    }
                }
            )
        }
    }

}