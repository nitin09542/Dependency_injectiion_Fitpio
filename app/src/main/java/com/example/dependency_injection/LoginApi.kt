package com.example.dependency_injection


import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface LoginApi {
    @GET("photos")
    suspend fun login(): Response<List<LoginResponse>>
}
