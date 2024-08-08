package com.example.prodjectformc.data.network

import android.util.Log
import com.example.prodjectformc.data.model.signin.SignInRequest
import com.example.prodjectformc.data.model.Response
import com.example.prodjectformc.data.model.general.AccountInfo
import com.example.prodjectformc.data.model.general.CurrentUser
import com.example.prodjectformc.data.model.signup.SignUpRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers

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

    override suspend fun getAccountInfo(token: String): AccountInfo? {
        return try {
            val response = client.get {
                url(HttpRoutes.INFORMATION)
                contentType(ContentType.Application.Json)
                headers.append(HttpHeaders.Authorization, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIzNDVoMDk4YmI4cmViZXJid3I0dnZiODk0NSIsImp0aSI6IjE0MTMwMzEwLWYyZTItNDk0My1iNzNiLTZhODc1NWY3YzE4MSIsImlhdCI6IjE3MjMxMjYzNDAiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6ImRkMzhmMzJkLTc1ODYtNGM1Yi05ZGExLTM4YTYxY2MxN2VhYyIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWUiOiJuZXNrbG1udmFuQGdtYWlsLmNvbSIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL2VtYWlsYWRkcmVzcyI6Im5lc2tsbW52YW5AZ21haWwuY29tIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoi0J_RgNC10L_QvtC00LDQstCw0YLQtdC70YwiLCJleHAiOjE3MjMyMTI3NDAsImlzcyI6IkV4YW1wbGVJc3N1ZXIiLCJhdWQiOiJWYWxpZEF1ZGllbmNlIn0.mBENTxMH1Rc4C_vwIFpk3ef5B2ZM1y9GPbzDE0oY5Kc")
            }
            response.body<AccountInfo>()
        } catch (e: Exception) {
            Log.d("Error ${e.message}", e.message.toString())
            return null
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