package com.example.valuedev.bidding

import retrofit2.Call
import retrofit2.http.*

interface BiddingService {
    @GET("hire")
    fun getBidding(@Query("limit") limit : Int?, @Query("search[idBioDev]") idBio : String?): Call<BiddingResponse>

    @FormUrlEncoded
    @PATCH("hire/{id}")
    fun patchBidding(@Path("id")id:String?,
                     @Field("statusConfirm") confirm: String?):Call<Confirm>
}