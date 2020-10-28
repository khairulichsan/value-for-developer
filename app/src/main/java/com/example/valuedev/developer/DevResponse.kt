package com.example.valuedev.developer

import com.google.gson.annotations.SerializedName

data class DevResponse (val success: String?, val message: String?, val data: Data?) {
    data class Data(
        @SerializedName("name") val name:String?,
        @SerializedName("job_desk") val jobdesk:String?,
        @SerializedName("status_job") val status:String?,
        @SerializedName("work_place") val place:String?,
        @SerializedName("description") val desc:String?,
        @SerializedName("city") val city:String?,
        @SerializedName("image") val image:String?,
        @SerializedName("id_dev") val id_dev:String?,
        @SerializedName("id_bio_dev") val id_bio:Int?


    )
}