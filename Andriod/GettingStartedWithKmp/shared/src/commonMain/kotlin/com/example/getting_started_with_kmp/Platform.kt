package com.example.getting_started_with_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform