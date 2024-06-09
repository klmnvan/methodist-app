package com.example.prodjectformc.api.network

import com.example.prodjectformc.api.Result
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow

interface PostsService {

    suspend fun sendCode(userEmail: String): Flow<Result<String>>
    suspend fun signIn(userEmail: String, userCode: String): String?

    companion object {
        fun create(): PostsService {
            return PostServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.BODY
                    }
                    install(HttpCookies)
                    install(ContentNegotiation){
                        json()
                    }

                }
            )
        }
    }

}