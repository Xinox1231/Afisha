package com.example.afisha

import com.example.afisha.pojo.MovieResponse
import com.example.afisha.pojo.ReviewsResponse
import com.example.afisha.pojo.TrailerResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie?limit=30&rating.kp=7-10&sortField=votes.kp&sortType=-1")
    @Headers(
        "X-API-KEY:${BuildConfig.API_KEY2}"
    )
    fun loadMovies(
        @Query("page")
        page: Int
    ): Single<MovieResponse>

    @GET("movie/{id}")
    @Headers(
        "X-API-KEY:${BuildConfig.API_KEY2}"
    )
    fun findById(
        @Path("id")
        id: String
    ): Single<TrailerResponse>

    @GET("review?limit=30&page=1")
    @Headers(
        "X-API-KEY:${BuildConfig.API_KEY2}"
    )
    fun loadReviews(
        @Query("movieId")
        movieId: Int
    ): Single<ReviewsResponse>
}
