package edu.learn.kotlin.coroutine

import kotlinx.coroutines.delay

fun main(args: Array<String>) {
    println("Start")
    println(Thread.currentThread().name)

    // Start a coroutine
    suspend {
        delay(500)
        print("Hello ")
        println(Thread.currentThread().name)
    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")
}