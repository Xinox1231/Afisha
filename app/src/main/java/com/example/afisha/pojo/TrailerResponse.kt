package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrailerResponse(
    @SerializedName("videos")
    @Expose
    val trailersList: TrailersList
)