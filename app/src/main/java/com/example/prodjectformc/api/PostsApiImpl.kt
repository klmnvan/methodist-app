package com.example.prodjectformc.api

import com.example.prodjectformc.api.notwork.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostsApiImpl(private val client: HttpClient) : PostsApi {

    override suspend fun sendCode(userEmail: String): String? {
        return client.post {
            url(HttpRoutes.SENDCODE)
            contentType(ContentType.Application.Json)
            headers {
                append("User-email", userEmail)
            }
        }.toString()
    }

}