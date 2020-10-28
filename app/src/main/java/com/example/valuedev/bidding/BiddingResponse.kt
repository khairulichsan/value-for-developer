package com.example.valuedev.bidding

import com.google.gson.annotations.SerializedName

data class BiddingResponse(val success:String?, val data: List<BioDev>) {
    data class BioDev(@SerializedName("idHire")val idHire : Int?,
                      @SerializedName("projectJob")val job : String?,
                      @SerializedName("message")val message : String?,
                      @SerializedName("statusConfirm")val confirm : String?,
                      @SerializedName("date")val dateConfirm : String?,
                      @SerializedName("price")val price : Int?,
                      @SerializedName("idBioDev")val idBio : Int?,
                      @SerializedName("idProject")val idProject : Int?,
                      @SerializedName("createdAt")val created_at : String?,
                      @SerializedName("updatedAt")val updated_at : String?)
}
