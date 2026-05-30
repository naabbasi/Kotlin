package com.example.getting_started_with_kmp.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

@Serializable
data class CreatePostRequest(
    val title: String,
    val body: String,
    val userId: Int
)

@Serializable
data class CreatePostResponse(
    val id: Int
)