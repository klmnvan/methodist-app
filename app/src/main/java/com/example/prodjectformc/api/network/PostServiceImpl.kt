package com.example.prodjectformc.api.network

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.prodjectformc.api.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class PostServiceImpl(private val client: HttpClient) : PostsService {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun sendCode(userEmail: String): Flow<Result<String>> {
        return flow {
            try {
                val request = client.post{
                    url(HttpRoutes.SENDCODE)
                    contentType(ContentType.Application.Json)
                    headers {
                        append("User-email", userEmail)
                    }
                }
                Result.Success(request)
            }
            catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Don't send code"))
                return@flow
            }

        }
    }


    override suspend fun signIn(userEmail: String, userCode: String): String? {
        TODO("Not yet implemented")
    }

}