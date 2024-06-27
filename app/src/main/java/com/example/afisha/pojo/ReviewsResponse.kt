package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("docs")
    @Expose
    val reviews: List<Review>? = null
)
