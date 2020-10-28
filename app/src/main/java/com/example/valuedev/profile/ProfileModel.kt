package com.example.valuedev.profile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class ProfileModel (
    val name_company: String?,
    val sector: String?,
    val city: String?,
    val desc: String?,
    val image: String?
)