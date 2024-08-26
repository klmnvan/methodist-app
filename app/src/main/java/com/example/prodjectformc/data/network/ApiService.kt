package com.example.prodjectformc.data.network

import com.example.prodjectformc.data.model.Response
import com.example.prodjectformc.data.model.createevent.GetFormOfWorksResponse
import com.example.prodjectformc.data.model.createevent.participation.CreateParticipationEventRequest
import com.example.prodjectformc.data.model.createevent.CreateEventResponse
import com.example.prodjectformc.data.model.createevent.participation.GetEventFormsResponse
import com.example.prodjectformc.data.model.createevent.participation.GetParticipationFormsResponse
import com.example.prodjectformc.data.model.createevent.participation.GetResultEventsResponse
import com.example.prodjectformc.data.model.createevent.participation.GetStatusEventsResponse
import com.example.prodjectformc.data.model.createevent.publication.CreatePublicationEventRequest
import com.example.prodjectformc.data.model.general.AccountInfo
import com.example.prodjectformc.data.model.general.EventModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ApiService {

    suspend fun signIn(email: String, password: String): Response
    suspend fun signUp(email: String, surname: String, name: String, patronymic: String, password: String, passwordConfirm: String): Response
    suspend fun getAccountInfo(token: String): AccountInfo?
    suspend fun getEvent(idEmploeyy: String, token: String): List<EventModel>?
    suspend fun getFormOfWorks(token: String): GetFormOfWorksResponse
    suspend fun getParticipationForms(token: String): GetParticipationFormsResponse
    suspend fun getStatusEvents(token: String): GetStatusEventsResponse
    suspend fun getEventForms(token: String): GetEventFormsResponse
    suspend fun getResultEvents(token: String): GetResultEventsResponse
    suspend fun createParticipationEvent(token: String, request: CreateParticipationEventRequest): CreateEventResponse
    suspend fun createPublicationEvent(token: String, request: CreatePublicationEventRequest): CreateEventResponse

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