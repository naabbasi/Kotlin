package com.softpak.datagenerator.utils

import java.io.File
import java.util.*

class AppSettings {
    companion object {
        private val properties = Properties()
        fun getProperty(key: String) : String {
           return properties.getProperty(key.toLowerCase())
        }

        /**
         * Load from resource within the system classloader.
         */
        fun fromResource(resourceName: String): Properties {
            val classLoader = ClassLoader.getSystemClassLoader()
            val url = classLoader.getResource(resourceName)

            properties.load(url.openStream())
            return properties
        }

        /**
         * Load from resource within the system disk
         */
        fun fromFile(fileName: String) : Properties {
            val file = File(fileName)
            if(file.exists()){
                properties.load(file.inputStream())
            }else{
                println("Invalid path or file name")
            }
            return properties
        }
    }
}