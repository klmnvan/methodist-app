package com.example.prodjectformc.data.network

import android.util.Log
import com.example.prodjectformc.data.model.signin.SignInRequest
import com.example.prodjectformc.data.model.Response
import com.example.prodjectformc.data.model.createevent.GetFormOfWorksResponse
import com.example.prodjectformc.data.model.createevent.participation.CreateParticipationEventRequest
import com.example.prodjectformc.data.model.createevent.CreateEventResponse
import com.example.prodjectformc.data.model.createevent.holding.CreateHoldingEventRequest
import com.example.prodjectformc.data.model.createevent.internship.CreateInternshipEventRequest
import com.example.prodjectformc.data.model.createevent.participation.GetEventFormsResponse
import com.example.prodjectformc.data.model.createevent.participation.GetParticipationFormsResponse
import com.example.prodjectformc.data.model.createevent.participation.GetResultEventsResponse
import com.example.prodjectformc.data.model.createevent.participation.GetStatusEventsResponse
import com.example.prodjectformc.data.model.createevent.publication.CreatePublicationEventRequest
import com.example.prodjectformc.data.model.general.AccountInfo
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.data.model.general.EventModelResponse
import com.example.prodjectformc.data.model.general.FormOfWork
import com.example.prodjectformc.data.model.signup.SignUpRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonPrimitive

class ApiServiceImpl (private val client: HttpClient): ApiService {

    override suspend fun signIn(email: String, password: String): Response {
        return try {
            val response = client.post {
                url(HttpRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                setBody(SignInRequest(email, password))
            }
            val responseBody = response.body<String?>()
            val cleanedResponse = responseBody?.let {
                Json.parseToJsonElement(it).jsonPrimitive.content
            }
            Response(cleanedResponse, null)
        } catch (e: RedirectResponseException) {
            Log.d("Error ${e.response.status.value}", e.message)
            return Response(null, "Ошибка ${e.response.status.value}")
        } catch (e: ClientRequestException) {
            if(e.response.body<String>().contains("Invalid password")){
                return Response(null,  "Неверный пароль")
            }
            if(e.response.body<String>().contains("Invalid email")){
                return Response(null,  "Неверная почта")
            }
            Log.d("Error ${e.response.status.value}", e.message)
            Response(null, "Ошибка ${e.response.status.value}")
        } catch (e: ServerResponseException) {
            Log.d("Error ${e.response.status.value}", e.message)
            Response(null, "Ошибка сервера")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Response(null, e.message)
        }
    }

    override suspend fun getAccountInfo(token: String): AccountInfo? {
        return try {
            val response = client.get {
                url(HttpRoutes.INFORMATION)
                contentType(ContentType.Application.Json)
                headers.append(HttpHeaders.Authorization, "Bearer ${CurrentUser.token}")
            }
            response.body<AccountInfo>()
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return null
        }
    }

    override suspend fun getEvent(idEmploeyy: String, token: String): List<EventModelResponse>? {
        return try {
            val response = client.get {
                url(HttpRoutes.GETEVENT)
                contentType(ContentType.Application.Json)
                headers {
                    append("IdEmploeyy", idEmploeyy)
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            response.body<MutableList<EventModelResponse>>()
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return null
        }
    }

    override suspend fun getFormOfWorks(token: String): GetFormOfWorksResponse {
        return try {
            val response = client.get {
                url(HttpRoutes.FORMOFWORKS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetFormOfWorksResponse(response.body<List<FormOfWork>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetFormOfWorksResponse(null, e.message)
        }
    }

    override suspend fun getParticipationForms(token: String): GetParticipationFormsResponse {
        return try {
            val response = client.get {
                url(HttpRoutes.GETPARTICIPATIONFORMS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetParticipationFormsResponse(response.body<List<String>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetParticipationFormsResponse(null, e.message)
        }
    }

    override suspend fun getStatusEvents(token: String): GetStatusEventsResponse {
        return try {
            val response = client.get {
                url(HttpRoutes.GETSTATUSEVENTS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetStatusEventsResponse(response.body<List<String>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetStatusEventsResponse(null, e.message)
        }
    }

    override suspend fun getEventForms(token: String): GetEventFormsResponse {
        return try {
            val response = client.get {
                url(HttpRoutes.GETEVENTFORMS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetEventFormsResponse(response.body<List<String>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetEventFormsResponse(null, e.message)
        }
    }

    override suspend fun getResultEvents(token: String): GetResultEventsResponse {
        return try {
            val response = client.get {
                url(HttpRoutes.GETRESULTEVENTS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetResultEventsResponse(response.body<List<String>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetResultEventsResponse(null, e.message)
        }
    }

    override suspend fun createParticipationEvent(
        token: String,
        request: CreateParticipationEventRequest
    ): CreateEventResponse {
        return try {
            val response = client.post {
                url(HttpRoutes.CREATEPARTICIPATIONEVENT)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
                setBody(request)
            }
            CreateEventResponse(response.body<EventModelResponse>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return CreateEventResponse(null, e.message)
        }
    }

    override suspend fun createPublicationEvent(
        token: String,
        request: CreatePublicationEventRequest
    ): CreateEventResponse {
        return try {
            val response = client.post {
                url(HttpRoutes.CREATEPUBLICATIONEVENT)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
                setBody(request)
            }
            CreateEventResponse(response.body<EventModelResponse>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return CreateEventResponse(null, e.message)
        }
    }

    override suspend fun createHoldingEvent(
        token: String,
        request: CreateHoldingEventRequest
    ): CreateEventResponse {
        return try {
            val response = client.post {
                url(HttpRoutes.CREATEHOLDINGEVENT)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
                setBody(request)
            }
            CreateEventResponse(response.body<EventModelResponse>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return CreateEventResponse(null, e.message)
        }
    }

    override suspend fun createInternshipEvent(
        token: String,
        request: CreateInternshipEventRequest
    ): CreateEventResponse {
        return try {
            val response = client.post {
                url(HttpRoutes.CREATEINTERNSHIPEVENT)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
                setBody(request)
            }
            CreateEventResponse(response.body<EventModelResponse>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return CreateEventResponse(null, e.message)
        }
    }

    override suspend fun signUp(email: String, surname: String, name: String, patronymic: String, password: String, passwordConfirm: String): Response {
        return try {
            val response = client.post {
                url(HttpRoutes.REGISTER)
                contentType(ContentType.Application.Json)
                setBody(SignUpRequest(email, name, password, passwordConfirm, patronymic, surname))
            }
            Response(response.body<String?>().toString(), null)
        } catch (e: RedirectResponseException) {
            Log.d("Error ${e.response.status.value}", e.message)
            Response(null, "Ошибка ${e.response.status.value}")
        } catch (e: ClientRequestException) {
            if(e.response.body<String>().contains("is already taken")){
                return Response(null, "Почта уже зарегистрирована")
            }
            Log.d("Error ${e.response.status.value}", e.message)
            Response(null, "Ошибка ${e.response.status.value}")
        } catch (e: ServerResponseException) {
            Log.d("Error ${e.response.status.value}", e.message)
            return Response(null, "Ошибка сервера")
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Response(null, e.message)
        }
    }



}