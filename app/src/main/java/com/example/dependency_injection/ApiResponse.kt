package com.example.dependency_injection

sealed class ApiResponse<out T> {
    object Loading : ApiResponse<Nothing>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorCode: Int, val errorMessage: String) : ApiResponse<Nothing>()
    constructor()
}

