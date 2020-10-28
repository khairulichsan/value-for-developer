package com.example.valuedev.bidding

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Confirm (val success:String?, val data: List<list>) {
    data class list(@SerializedName
                     ("statusConfirm")val confirm : String?)
}
