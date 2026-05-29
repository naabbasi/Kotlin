package com.softpak.datagenerator.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


val random = Random()

fun randomDate(min: Int, max: Int, format: String) : String {
    var minDay = LocalDate.of(min, 1, 1).toEpochDay().toInt()
    var maxDay = LocalDate.of(max, 1, 1).toEpochDay().toInt()
    var randomDay = (minDay + random.nextInt(maxDay - minDay)).toLong()

    val formatter = DateTimeFormatter.ofPattern(format)
    return LocalDate.ofEpochDay(randomDay).format(formatter)
}

fun rand(from: Int, to: Int) : Int {
    return random.nextInt(to - from) + from
}

fun rand(from: Double, to: Double, precision: Int = 3) : String {
    return precision(from + (to - from) * random.nextDouble(), precision)
}

fun precision(number: Double, precision: Int) : String {
    return when (precision) {
        2 -> { String.format("%.2f", number) }
        3 -> { String.format("%.3f", number) }
        4 -> { String.format("%.4f", number) }
        else -> throw IllegalArgumentException()
    }
}
