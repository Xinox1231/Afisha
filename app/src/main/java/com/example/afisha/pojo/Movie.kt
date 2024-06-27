package com.example.afisha.pojo

import android.media.tv.TvContract.Channels.Logo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes.Name


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
    var enName: Any? = null,

    @SerializedName("names")
    @Expose
    var names: List<Name>? = null,

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
    var description: Any? = null,

    @SerializedName("shortDescription")
    @Expose
    var shortDescription: Any? = null,

    @SerializedName("status")
    @Expose
    var status: Any? = null,

    @SerializedName("rating")
    @Expose
    var rating: Rating? = null,

    @SerializedName("movieLength")
    @Expose
    var movieLength: Int? = null,

    @SerializedName("totalSeriesLength")
    @Expose
    var totalSeriesLength: Any? = null,

    @SerializedName("seriesLength")
    @Expose
    var seriesLength: Any? = null,

    @SerializedName("ratingMpaa")
    @Expose
    var ratingMpaa: Any? = null,

    @SerializedName("ageRating")
    @Expose
    var ageRating: Any? = null,

    @SerializedName("poster")
    @Expose
    var poster: Poster? = null,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null,

    @SerializedName("ticketsOnSale")
    @Expose
    var ticketsOnSale: Boolean? = null,

    @SerializedName("top10")
    @Expose
    var top10: Any? = null,

    @SerializedName("top250")
    @Expose
    var top250: Any? = null,

    @SerializedName("isSeries")
    @Expose
    var isSeries: Boolean? = null,

    @SerializedName("logo")
    @Expose
    var logo: Logo? = null
)