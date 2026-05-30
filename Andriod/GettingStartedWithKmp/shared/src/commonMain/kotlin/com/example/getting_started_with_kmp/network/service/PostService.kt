package com.example.getting_started_with_kmp.network.service

import com.example.getting_started_with_kmp.models.CreatePostRequest
import com.example.getting_started_with_kmp.models.CreatePostResponse
import com.example.getting_started_with_kmp.models.Post
import com.example.getting_started_with_kmp.network.ApiClient
import com.example.getting_started_with_kmp.network.ApiResponse
import com.example.getting_started_with_kmp.network.safeApiCall
import io.ktor.client.request.parameter

class PostService {
    
    // GET - Fetch all posts
    suspend fun fetchAllPosts(): List<Post> {
        return try {
            ApiClient.get("/posts")
        } catch (e: Exception) {
            // Handle error appropriately
            throw Exception("Failed to fetch posts: ${e.message}")
        }
    }

    suspend fun fetchAllPostsSafe(): ApiResponse<List<Post>> {
        return safeApiCall {
            ApiClient.get("/posts")
        }
    }
    
    // GET with path parameter - Fetch single post
    suspend fun fetchPostById(id: Int): Post {
        return ApiClient.get("/posts/$id")
    }
    
    // GET with query parameters
    suspend fun fetchPostsByUser(userId: Int): List<Post> {
        return ApiClient.get("/posts") {
            parameter("userId", userId)
        }
    }
    
    // POST - Create new post
    suspend fun createPost(title: String, body: String, userId: Int): CreatePostResponse {
        val request = CreatePostRequest(title, body, userId)
        return ApiClient.post("/posts", request)
    }
}