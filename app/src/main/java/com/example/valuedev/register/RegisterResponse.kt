package com.example.valuedev.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(val success: Boolean, val message: String?, val data: DataResult?) {

    data class DataResult(
        @SerializedName("name") val name: String?,
        @SerializedName("email") val email: String?,
        @SerializedName("no_hp") val phoneNumber: String?,
        @SerializedName("role") val role: String?
    )
}