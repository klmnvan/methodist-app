package com.example.prodjectformc.api

import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun sendCodeEmail(email:String): Flow<Result<String>>

}