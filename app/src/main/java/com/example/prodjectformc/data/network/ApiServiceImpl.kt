package com.example.prodjectformc.data.network

import android.util.Log
import com.example.prodjectformc.data.model.signin.SignInRequest
import com.example.prodjectformc.data.model.Response
import com.example.prodjectformc.data.model.createevent.GetFormOfWorksResponce
import com.example.prodjectformc.data.model.createevent.GetParticipationFormsResponce
import com.example.prodjectformc.data.model.general.AccountInfo
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.general.EventModel
import com.example.prodjectformc.data.model.general.FormOfWork
import com.example.prodjectformc.data.model.general.Specifications
import com.example.prodjectformc.data.model.home.RequestGetEventModel
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

    override suspend fun getEvent(idEmploeyy: String, token: String): List<EventModel>? {
        return try {
            val response = client.get {
                url(HttpRoutes.GETEVENT)
                contentType(ContentType.Application.Json)
                headers {
                    append("IdEmploeyy", idEmploeyy)
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            response.body<MutableList<EventModel>>()
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return null
        }
    }

    override suspend fun getFormOfWorks(token: String): GetFormOfWorksResponce {
        return try {
            val response = client.get {
                url(HttpRoutes.FORMOFWORKS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetFormOfWorksResponce(response.body<List<FormOfWork>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetFormOfWorksResponce(null, e.message)
        }
    }

    override suspend fun getParticipationForms(token: String): GetParticipationFormsResponce {
        return try {
            val response = client.get {
                url(HttpRoutes.GETPARTICIPATIONFORMS)
                contentType(ContentType.Application.Json)
                headers {
                    append(HttpHeaders.Authorization, "Bearer ${token}")
                }
            }
            GetParticipationFormsResponce(response.body<List<String>>(), null)
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return GetParticipationFormsResponce(null, e.message)
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