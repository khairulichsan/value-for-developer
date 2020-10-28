package com.example.valuedev.biodata

import com.google.gson.annotations.SerializedName

class BiodataResponse (val success:String?, val data: resadd) {
    data class resadd ( @SerializedName("name_project") val name : String?,
                       val loc : String?,
                       val projectDesc : String?,
                       val deadline : String?,
                       val idReq : String?,
                       val image : String?)
}