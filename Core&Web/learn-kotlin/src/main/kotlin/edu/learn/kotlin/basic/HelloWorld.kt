package edu.learn.kotlin.basic

fun main(args: Array<String>) {
    println("Hello World")

    for(i in 1..10 step 2){
        println("Hello World " + i)
    }

    val list = mutableListOf<String>()
    list.add("Hello World 1....")
    list.add("Hello World 2....")
    list.add("Hello World 3....")
    list.add("Hello World 4....")
    list.add("Hello World 5....")
}