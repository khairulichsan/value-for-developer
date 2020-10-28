package com.example.valuedev.developer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DevService {

    @GET("bio-developer/{id_bio_dev}")
    fun getDevRequest(@Path("id_bio_dev") id_bio_dev: String): Call<DevResponse>

}