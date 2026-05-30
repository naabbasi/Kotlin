package com.example.getting_started_with_kmp.network

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}

// Extension function for safe API calls [citation:8]
suspend fun <T> safeApiCall(execute: suspend () -> T): ApiResponse<T> {
    return try {
        ApiResponse.Success(execute())
    } catch (e: io.ktor.client.plugins.ClientRequestException) {
        ApiResponse.Error("HTTP ${e.response.status.value}: ${e.message}")
    } catch (e: io.ktor.client.plugins.ServerResponseException) {
        ApiResponse.Error("Server error: ${e.message}")
    } catch (e: java.io.IOException) {
        ApiResponse.Error("Network error: ${e.message}")
    } catch (e: Exception) {
        ApiResponse.Error("Unknown error: ${e.message}")
    }
}