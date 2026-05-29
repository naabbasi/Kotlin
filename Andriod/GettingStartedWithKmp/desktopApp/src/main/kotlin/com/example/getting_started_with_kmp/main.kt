package com.example.getting_started_with_kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gettingstartedwithkmp",
    ) {
        App()
    }
}