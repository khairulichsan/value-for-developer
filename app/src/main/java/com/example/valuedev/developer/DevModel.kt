package com.example.valuedev.developer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class DevModel(
    val name: String?,
    val jobdesk: String?,
    val status: String?,
    val place: String?,
    val desc: String?,
    val city: String?,
    val image: String?
)