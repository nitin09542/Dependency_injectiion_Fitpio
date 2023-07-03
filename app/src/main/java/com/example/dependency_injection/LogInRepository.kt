package com.example.dependency_injection

import com.example.dependency_injection.ErrorHandlingClass.Companion.parseError
import javax.inject.Inject

class LogInRepository @Inject constructor(private val loginApi: LoginApi) {
    suspend fun hitlogInApi(): ApiResponse<List<LoginResponse>> {
        try {
            val response = loginApi.login()
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    return ApiResponse.Success(loginResponse)
                } else {
                    return ApiResponse.Error(response.code(), "Empty response body")
                }
            } else {
                val errorBody = response.errorBody()?.string()
                if (errorBody != null) {
                    val apiError = parseError(errorBody)
                    return ApiResponse.Error(
                        response.code(),
                        apiError?.errorMessage ?: "Unknown error"
                    )
                } else {
                    return ApiResponse.Error(response.code(), "Empty error body")
                }
            }
        } catch (e: Exception) {
            return ApiResponse.Error(0, e.message ?: "Unknown exception")
        }
        return ApiResponse.Error(0, "Unknown exception")
    }
}
