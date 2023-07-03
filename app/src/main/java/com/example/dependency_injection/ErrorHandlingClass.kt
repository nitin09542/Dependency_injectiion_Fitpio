package com.example.dependency_injection

import com.google.gson.Gson
import com.google.gson.JsonParseException

class ErrorHandlingClass {
    companion object {
        fun parseError(responseBody: String): ApiError? {
            return try {
                val gson = Gson()
                gson.fromJson(responseBody, ApiError::class.java)
            } catch (e: JsonParseException) {
                null
            }
        }
    }
}