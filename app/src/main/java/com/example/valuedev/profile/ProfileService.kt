package com.example.valuedev.profile

import com.example.valuedev.profile.port.PortResponse
import com.example.valuedev.profile.skill.SkillResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ProfileService {
    @GET("bio-developer")
    fun getProfileDev(@Query("search[id_dev]") id: String?): Call<ProfileResponse>

    @FormUrlEncoded
    @POST("skill")
    suspend fun postSkill(@Field("id_bio_dev") id_bio: String?,
                  @Field("name_skill") skill: String?,): SkillResponse
    @Multipart
    @POST("port")
    suspend fun postPort(
            @Part("name_app") name : RequestBody,
            @Part("description") desc: RequestBody,
            @Part("link_repo") link_repo: RequestBody,
            @Part("link_publish") link_publish: RequestBody,
            @Part("workplace_related") place: RequestBody,
            @Part("base_type") type: RequestBody,
            @Part("id_bio_dev") idBio: RequestBody,
            @Part image: MultipartBody.Part
    ): PortResponse
}