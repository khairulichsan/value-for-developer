package com.example.valuedev.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(val success: Boolean, val message: String?, val data: DataResult) {

    data class DataResult(
        @SerializedName("id_dev") val idAccount: Int?,
        @SerializedName("name") val name: String?,
        val email: String?,
        val role: Int?,
        val token: String?
    )
}