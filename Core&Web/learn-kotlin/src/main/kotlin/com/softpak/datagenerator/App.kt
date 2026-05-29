package com.softpak.datagenerator

import com.softpak.datagenerator.generate.FirmAndUserUtils
import com.softpak.datagenerator.generate.PriceUtils
import com.softpak.datagenerator.utils.AppSettings
import com.softpak.datagenerator.utils.FileUtils
import rx.Observable


fun main(args : Array<String>){

    AppSettings.fromResource("app.properties")
    //AppSettings.fromFile("app.properties")
    PriceUtils.getPriceMap()

    val file = FileUtils()
    file.isFirmIndexExists()
    file.isAccountIndexExists()
    file.isModelIndexExists()
    readConsole()
}

enum class Options {
    FIRMS_USERS, ACCOUNTS_ONLY, MODEL_ONLY, ALL
}

fun readConsole(){
    while(true){
        try {
            printChars("-")
            println("1. Want to generate only firm(s)")
            println("2. Want to generate only account(s)")
            println("3. Want to generate only model(s)")
            println("4. Want to generate all")
            println("5. Exit")
            printChars("-")
            print("Please select: ")
            val read = readLine()!!.trim().toInt()
            when(read){
                1 -> {
                    val firmUtils = FirmAndUserUtils(Options.FIRMS_USERS)
                    firmUtils.generate()
                }
                2 -> {
                    val firmUtils = FirmAndUserUtils(Options.ACCOUNTS_ONLY)
                    firmUtils.generate()
                }
                3 -> {
                    val firmUtils = FirmAndUserUtils(Options.MODEL_ONLY)
                    firmUtils.generate()
                }
                4 -> {
                    val firmUtils = FirmAndUserUtils(Options.ALL)
                    firmUtils.generate()
                }
                5 -> {
                    System.exit(0)
                }
                else -> readConsole()
            }
        }catch (ex: Exception) {
            when (ex) {
                is NumberFormatException -> readConsole()
            }
        }
    }
}


fun printChars(char : String, number: Int = 30){
    Observable.just(number)
            .map { char.repeat(it) }
            .subscribe {println(it)}
}


