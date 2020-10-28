package com.example.valuedev.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(val success:String?, val data: List<BioDev>) {
    data class BioDev(val id_bio_dev: String?,
    @SerializedName("name")val name : String?,
                      @SerializedName("job_desk")val job_desk : String?,
                      @SerializedName("status_job")val status_job : String?,
                      @SerializedName("work_place")val work_place : String?,
                      @SerializedName("description")val description : String?,
                      @SerializedName("city")val city : String?,
                      @SerializedName("image")val image : String?,
                      @SerializedName("id_dev")val id_dev : String?,
                      @SerializedName("created_at")val created_at : String?,
                      @SerializedName("updated_at")val updated_at : String?,
                      @SerializedName("skill")val skill : String?)
}