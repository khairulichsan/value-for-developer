package com.example.valuedev.login

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiService {

    @FormUrlEncoded
    @POST("developer/login")
    suspend fun loginRequest(
        @Field("email") email: String?,
        @Field("password") password: String?,
    ) : LoginResponse
}