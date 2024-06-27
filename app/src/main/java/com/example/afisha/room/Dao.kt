package com.example.afisha.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.afisha.pojo.Movie
import io.reactivex.rxjava3.core.Completable

@Dao
interface Dao {

    @Query("SELECT * FROM favourite_movies")
    fun getFavouriteMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId")
    fun getFavouriteMovie(movieId: Int): LiveData<Movie>

    @Insert
    fun insertMovie(movie: Movie): Completable

    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    fun removeMovie(movieId: Int): Completable
}
