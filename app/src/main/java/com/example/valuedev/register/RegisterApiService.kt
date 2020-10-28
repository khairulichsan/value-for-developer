package com.example.valuedev.register

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApiService {

    @FormUrlEncoded
    @POST("developer/register")
    suspend fun registerRequest(
        @Field("name") name: String?,
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("no_hp") phoneNumber: String?
    ) : RegisterResponse
}