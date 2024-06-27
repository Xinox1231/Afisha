package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Poster (
    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("previewUrl")
    @Expose
    var previewUrl: String? = null
): Serializable
