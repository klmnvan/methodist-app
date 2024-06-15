package com.example.prodjectformc.data.network

import android.util.Log
import com.example.prodjectformc.data.model.signin.SignInRequest
import com.example.prodjectformc.data.model.Response
import com.example.prodjectformc.data.model.signup.SignUpRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiServiceImpl (private val client: HttpClient): ApiService {

    override suspend fun signIn(email: String, password: String): Response {
        return try {
            val response = client.post {
                url(HttpRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                setBody(SignInRequest(email, password))
            }
            Response(response.body<String?>().toString(), null)
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