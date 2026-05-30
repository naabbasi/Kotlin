package com.example.getting_started_with_kmp.network.service

import com.example.getting_started_with_kmp.models.UserProfile
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object AuthApiClient {
    private var authToken: String? = null
    
    fun setAuthToken(token: String) {
        authToken = token
    }
    
    private val client = HttpClient() {
        defaultRequest {
            url("https://api.yourservice.com/v1")
            contentType(ContentType.Application.Json)
        }
        
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        
        // Automatically add Authorization header when token exists [citation:8]
        defaultRequest {
            authToken?.let {
                headers.append(HttpHeaders.Authorization, "Bearer $it")
            }
        }
    }
    
    suspend fun getProfile(): HttpResponse {
        return client.get("/user/profile")
    }
}