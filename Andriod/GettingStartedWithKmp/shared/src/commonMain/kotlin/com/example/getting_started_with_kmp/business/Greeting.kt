package com.example.getting_started_with_kmp.business

import com.example.getting_started_with_kmp.getPlatform
import com.example.getting_started_with_kmp.utils.sayHello

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return sayHello(platform.name)
    }
}