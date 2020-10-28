package com.example.valuedev.profile.port

import com.google.gson.annotations.SerializedName
import retrofit2.http.Part

class PortResponse (val success:String?, val data: PortRes) {
    data class PortRes ( @SerializedName("name_app") val name : String?,
                       val desc : String?,
                       val link_repo : String?,
                       val link_publish : String?,
                       val place : String?,
                        val type : String?,
                        val idBio : String?,
                       val image : String?)
}
