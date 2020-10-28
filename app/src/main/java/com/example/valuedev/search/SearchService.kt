package com.example.valuedev.search

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("bio-developer/")
    fun getSearch(
        @Query("search") search: String,
        @Query("order") order: String,
        @Query("page") page: Int
    ): Call<SearchResponse>
}