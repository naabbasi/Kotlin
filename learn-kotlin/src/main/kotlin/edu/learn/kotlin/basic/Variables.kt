package edu.learn.kotlin.basic

import java.math.BigDecimal

class Variables {
    val message = "Hello Noman"
    var counter = 0
    var shares = 5.5
    val amount : BigDecimal = BigDecimal("10002553643.255565")
}

fun main(args: Array<String>) {
    val variables = Variables()
    println(variables.message)
    println(variables.counter)
    println(variables.shares)
    println(variables.amount)
}