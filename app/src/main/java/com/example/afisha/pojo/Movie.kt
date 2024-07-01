package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Movie(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("alternativeName")
    @Expose
    var alternativeName: String? = null,

    @SerializedName("enName")
    @Expose
    var enName: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("typeNumber")
    @Expose
    var typeNumber: Int? = null,

    @SerializedName("year")
    @Expose
    var year: Int? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("shortDescription")
    @Expose
    var shortDescription: String? = null,

    @SerializedName("rating")
    @Expose
    var rating: Rating? = null,

    @SerializedName("movieLength")
    @Expose
    var movieLength: Int? = null,

    @SerializedName("poster")
    @Expose
    var poster: Poster? = null,

    @SerializedName("videos")
    @Expose
    var videos: TrailerResponse? = null,

    @SerializedName("ticketsOnSale")
    @Expose
    var ticketsOnSale: Boolean? = null,

    @SerializedName("isSeries")
    @Expose
    var isSeries: Boolean? = null,
): Serializable