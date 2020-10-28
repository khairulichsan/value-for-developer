package com.example.valuedev.util.response

import com.google.gson.annotations.SerializedName

data class ExperienceResponse(
    val success: Boolean?,
    val message: String?,
    val data: List<Experience>?
) {
    data class Experience(
        @SerializedName("id_exp") val idExperience: Int?,
        @SerializedName("name_company") val companyName: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("position") val workPosition: String?,
        @SerializedName("start") val start: String?,
        @SerializedName("end") val end: String?,
        @SerializedName("id_bio_dev") val idDev: Int?
    )
}