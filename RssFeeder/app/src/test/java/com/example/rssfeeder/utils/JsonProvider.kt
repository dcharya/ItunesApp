package com.example.rssfeeder.utils

import java.io.File
import java.io.IOException
import java.util.*

object JsonProvider {
    private val TAG = JsonProvider::class.java
    val DEFAULT_ERROR_BODY = "500, An Error Occurred"
    val EMPTY_JSON_ERROR_BODY = "{}"

    @Throws(IOException::class)
    fun getJson(path: String): String {
        val _path = "/json$path"
        return Scanner(JsonProvider::class.java.getResourceAsStream(_path)).useDelimiter("\\Z").next()
    }

    fun getJsonString(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader.getResource("/json$path")
        val file = File(uri.path)
        return String(file.readBytes())
    }
}