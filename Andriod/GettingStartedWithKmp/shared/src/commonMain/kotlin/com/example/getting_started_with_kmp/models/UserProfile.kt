package com.example.getting_started_with_kmp.models

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val phone: String? = null,
    val website: String? = null,
    val address: Address? = null,
    val company: Company? = null
)

@Serializable
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoLocation? = null
)

@Serializable
data class GeoLocation(
    val lat: String, val lng: String
)

@Serializable
data class Company(
    val name: String, val catchPhrase: String? = null, val bs: String? = null
)

// Authentication related models
@Serializable
data class LoginRequest(
    val username: String, val password: String
)

@Serializable
data class LoginResponse(
    val token: String, val userId: Int, val expiresIn: Long? = null
)

@Serializable
data class ErrorResponse(
    val error: String, val message: String? = null, val statusCode: Int? = null
)