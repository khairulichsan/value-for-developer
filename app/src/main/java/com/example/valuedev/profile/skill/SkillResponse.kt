package com.example.valuedev.profile.skill

import com.google.gson.annotations.SerializedName

class SkillResponse(val success: Boolean, val message: String?, val data: DataResult?) {

        data class DataResult(
                @SerializedName("id_bio_dev") val id_bio: String?,
                @SerializedName("name_skill") val skill: String?,
        )
    }