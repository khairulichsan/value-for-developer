package com.example.valuedev.util.service

import com.example.valuedev.util.response.ExperienceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExperienceApiService {
    @GET("exp/")
    suspend fun getAllExperienceById(@Query("search[id_bio_dev]") idDev: Int): ExperienceResponse
}