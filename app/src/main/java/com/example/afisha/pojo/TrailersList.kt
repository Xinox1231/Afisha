package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersList(
    @SerializedName("trailers")
    @Expose
    val trailers: List<Trailer>? = null
)