package com.example.valuedev.home

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET("bio-developer/")
    fun getAllEmployee(): Call<HomeResponse>
}