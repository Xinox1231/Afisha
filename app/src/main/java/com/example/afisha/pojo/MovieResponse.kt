package com.example.afisha.pojo

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("docs")
    val movies: List<Movie>? = null
)
