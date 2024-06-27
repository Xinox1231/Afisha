package com.example.afisha.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Review(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("movieId")
    @Expose
    var movieId: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("review")
    @Expose
    var review: String? = null,

    @SerializedName("date")
    @Expose
    var date: String? = null,

    @SerializedName("author")
    @Expose
    var author: String? = null,

    @SerializedName("userRating")
    @Expose
    var userRating: Int? = null,

    @SerializedName("authorId")
    @Expose
    var authorId: Int? = null,

    @SerializedName("createdAt")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updatedAt")
    @Expose
    var updatedAt: String? = null,

    @SerializedName("reviewDislikes")
    @Expose
    var reviewDislikes: Int? = null,

    @SerializedName("reviewLikes")
    @Expose
    var reviewLikes: Int? = null,
)
