package com.example.valuedev.util.service

import com.example.valuedev.util.response.PortfolioResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PortfolioApiService {
    @GET("port/")
    suspend fun getAllPortfolioById(@Query("search[id_bio_dev]") idDev: Int): PortfolioResponse
}