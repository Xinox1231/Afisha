package com.example.afisha

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.afisha.room.MovieDatabase

class FavoriteMoviesViewModel(application: Application): AndroidViewModel(application) {

    private val movieDao = MovieDatabase.getInstance(application).movieDao()
    val favouriteMoviesList = movieDao.getFavouriteMovies()
}