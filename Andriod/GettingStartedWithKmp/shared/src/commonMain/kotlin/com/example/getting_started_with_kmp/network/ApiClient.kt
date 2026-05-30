package com.example.getting_started_with_kmp.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiClient {
    // Create a single client instance for the whole app
    @PublishedApi
    internal val client = HttpClient() {
        // Base URL - all requests start with this
        defaultRequest {
            url("https://jsonplaceholder.typicode.com")  // Example free API
            contentType(ContentType.Application.Json)
        }
        
        // Automatically parse JSON
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true  // Critical for evolving APIs [citation:2]
                isLenient = true
            })
        }
        
        // Log all network calls (debug only)
        install(Logging) {
            level = LogLevel.BODY
        }
        
        // Set timeouts
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 30000
        }
    }
    
    suspend inline fun <reified T> get(endpoint: String, noinline block: HttpRequestBuilder.() -> Unit = {}): T {
        return client.get(endpoint, block = block).body()
    }
    
    suspend inline fun <reified T> post(endpoint: String, body: Any? = null): T {
        return client.post(endpoint) {
            contentType(ContentType.Application.Json)
            if (body != null) setBody(body)
        }.body()
    }
}