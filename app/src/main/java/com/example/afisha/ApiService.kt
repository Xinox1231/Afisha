package com.example.afisha

import com.example.afisha.pojo.Movie
import com.example.afisha.pojo.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie?limit=10&rating.kp=7-10&sortField=votes.kp&sortType=-1")
    @Headers(
        "X-API-KEY:${BuildConfig.API_KEY}"
    )
    fun loadMovies(
        @Query("page")
        page: Int
    ): Single<MovieResponse>

    @GET("movie/{id}")
    @Headers(
        "X-API-KEY:${BuildConfig.API_KEY}"
    )
    fun findById(
        @Path("id")
        id: String
    ): Single<Movie>
}