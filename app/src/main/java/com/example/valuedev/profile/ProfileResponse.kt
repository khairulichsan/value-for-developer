package com.example.valuedev.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(val success: String?, val message: String?, val data: List<DataResult>?) {
    data class DataResult(
        @SerializedName("name") val name_company:String?,
        @SerializedName("job_desk") val sector:String?,
        @SerializedName("status_job") val status:String?,
        @SerializedName("description") val desc:String?,
        @SerializedName("work_place") val city:String?,
        @SerializedName("image") val image:String?,
        @SerializedName("id_dev") val id_rec:String?,
        @SerializedName("id_bio_dev") val id_bio:Int?

    )
}