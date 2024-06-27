package com.example.afisha.pojo

import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerResponse(

    @Embedded
    @SerializedName("videos")
    @Expose
    val trailersList: TrailersList? = null
)
