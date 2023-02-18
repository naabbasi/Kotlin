package com.softpak.datagenerator.utils

import java.io.File

class FileUtils {
    fun isFirmIndexExists() {
        val file = File("f.idx")
        if(!file.exists()){
            file.writeText(0.toString())
        }
    }

    fun writeFirmIndex(indexValue : Int){
        val file = File("f.idx")
        file.writeText(indexValue.toString())
    }

    fun readFirmIndex() : Int{
        val file = File("f.idx")
        var index = 0
        file.useLines { lines -> lines.forEach{
            index = it.toInt()
        } }

        return index
    }

    fun isAccountIndexExists() {
        val file = File("a.idx")
        if(!file.exists()){
            file.writeText(0.toString())
        }
    }

    fun writeAccountIndex(indexValue : Int){
        val file = File("a.idx")
        file.writeText(indexValue.toString())
    }

    fun readAccountIndex() : Int{
        val file = File("a.idx")
        var index = 0
        file.useLines { lines -> lines.forEach{
            index = it.toInt()
        } }

        return index
    }

    fun isModelIndexExists() {
        val file = File("m.idx")
        if(!file.exists()){
            file.writeText(0.toString())
        }
    }

    fun writeModelIndex(indexValue : Int){
        val file = File("m.idx")
        file.writeText(indexValue.toString())
    }

    fun readModelIndex() : Int{
        val file = File("m.idx")
        var index = 0
        file.useLines { lines -> lines.forEach{
            index = it.toInt()
        } }

        return index
    }
}