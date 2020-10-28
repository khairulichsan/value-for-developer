package com.example.valuedev.biodata

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BiodataService {

    @Multipart
    @POST("bio-developer")
    suspend fun postBio(
            @Part("name") name : RequestBody,
            @Part("status_job") job: RequestBody,
            @Part("job_desk") desk: RequestBody,
            @Part("city") city: RequestBody,
            @Part("work_place") place: RequestBody,
            @Part("id_dev") idDev: RequestBody,
            @Part("description") desc: RequestBody,
            @Part image: MultipartBody.Part
    ): BiodataResponse

}