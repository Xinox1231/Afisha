package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Trailer(

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("site")
    @Expose
    var site: String? = null,

    @SerializedName("size")
    @Expose
    var size: Int? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null
) 