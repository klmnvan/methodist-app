package com.example.prodjectformc.api

import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

object AppModule {

    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation){
            json()
        }
    }

    fun providePostsApi(client: HttpClient): PostsApi = PostsApiImpl(client)

    fun providePostsRepository(api: PostsApi): PostsRepository = PostsRepositoryImpl(api)

}