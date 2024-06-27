package com.example.afisha

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.afisha.pojo.Movie
import com.example.afisha.pojo.Review
import com.example.afisha.pojo.Trailer
import com.example.afisha.room.Dao
import com.example.afisha.room.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailsViewModel(application: Application, movieId: Int) :
    AndroidViewModel(application) {

    companion object {
        private const val TAG = "MovieDetailViewModel"
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val movieDao: Dao

    init {
        loadTrailers(movieId)
        loadReviews(movieId)
        movieDao = MovieDatabase.getInstance(application).movieDao()
    }

    val favouriteMovies = movieDao.getFavouriteMovie(movieId)


    private var reviewPage = 1
    private val _trailersList = MutableLiveData<List<Trailer>>()
    val trailersList: LiveData<List<Trailer>>
        get() = _trailersList

    private val _reviewsList = MutableLiveData<List<Review>>()
    val reviewsList: LiveData<List<Review>>
        get() = _reviewsList

    fun insertMovie(movie: Movie) {
        val disposable = movieDao.insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun deleteMovie(movieId: Int) {
        val disposable = movieDao.removeMovie(movieId)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun loadTrailers(movieId: Int) {
        val disposable = ApiFactory.apiService.findById("$movieId")
            .subscribeOn(Schedulers.io())
            .map { it.trailersList?.trailers ?: listOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _trailersList.value = it
                Log.d(TAG, it.toString())
            }, {
                Log.d(TAG, it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    fun loadReviews(movieId: Int) {
        //Сделать проверку: загружается ли уже страница с отзывами
        val disposable = ApiFactory.apiService.loadReviews(movieId)
            .subscribeOn(Schedulers.io())
            .map { it.reviews ?: listOf() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val loadedReviews = _reviewsList.value?.toMutableList()
                if (loadedReviews != null) {
                    loadedReviews.addAll(it)
                    _reviewsList.value = loadedReviews ?: listOf()

                } else {
                    _reviewsList.value = it
                }
                reviewPage++
                Log.d(TAG, it.toString())
            }, {
                Log.d(TAG, it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
