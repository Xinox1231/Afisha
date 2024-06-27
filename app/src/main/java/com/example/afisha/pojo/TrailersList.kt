
package com.example.afisha.pojo

import androidx.room.Embedded
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailersList(

    @Embedded
    @SerializedName("trailers")
    @Expose
    val trailers: List<Trailer>? = null
)