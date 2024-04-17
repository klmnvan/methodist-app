package com.example.prodjectformc.viewmodels

data class PostsState(
    val posts: String? = null,
    val loading: Boolean = false,
    val error: String? = null
)
