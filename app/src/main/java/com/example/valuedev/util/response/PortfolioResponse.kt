package com.example.valuedev.util.response

import com.google.gson.annotations.SerializedName

data class PortfolioResponse(
    val success: Boolean?,
    val message: String?,
    val data: List<Portfolio>?
) {
    data class Portfolio(
        @SerializedName("id_port") val idPortfolio: Int?,
        @SerializedName("name_app") val namePortfolio: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("link_repo") val linkRepo: String?,
        @SerializedName("link_publish") val linkPublish: String?,
        @SerializedName("workplace_related") val workplaceRelated: String?,
        @SerializedName("base_type") val typePortfolio: String?,
        @SerializedName("image") val imagePortfolio: String?,
        @SerializedName("id_bio_dev") val idDev: Int?
    )
}

